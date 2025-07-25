package com.example.lecturaparaprimaria.data

import android.util.Log
import androidx.work.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit

class SyncRepository(private val appDatabase: AppDatabase) {
    private val firestore = FirebaseFirestore.getInstance()
    private val usuariosCollection = firestore.collection("usuarios")

    suspend fun syncUsuarios() {
        try {
            val usuariosLocales = appDatabase.usuarioDao().obtenerTodos().map { it.nombre }.toSet()
            val usuariosFirestore = mutableListOf<Usuario>()

            val salonesSnapshot = firestore.collection("salones").get().await()

            for (document in salonesSnapshot.documents) {
                val alumnos = document.get("alumnos") as? List<Map<String, Any>> ?: continue
                for (alumno in alumnos) {
                    val nombre = alumno["usuario"] as? String ?: continue
                    val avatarId = (alumno["avatarId"] as? Long)?.toInt() ?: -1
                    usuariosFirestore.add(Usuario(nombre = nombre, avatarId = avatarId))
                }
            }

            val nombresRemotos = usuariosFirestore.map { it.nombre }.toSet()

            // ✅ Eliminar usuarios locales que ya no están en Firebase
            usuariosLocales
                .filterNot { it in nombresRemotos }
                .forEach { nombre ->
                    appDatabase.usuarioDao().obtenerPorNombre(nombre)?.let {
                        appDatabase.usuarioDao().eliminar(it)
                    }
                }

            // ✅ Insertar o actualizar usuarios que vienen de Firebase
            usuariosFirestore.forEach { usuario ->
                appDatabase.usuarioDao().insertar(usuario) // Reemplaza si ya existe por clave primaria
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    // Programar sincronización periódica con WorkManager
    fun scheduleSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(
            repeatInterval = 1, // Cada 1 hora (ajusta según necesites)
            repeatIntervalTimeUnit = TimeUnit.HOURS
        ).setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueue(syncRequest)
    }

    suspend fun syncAvatar(nombre: String, avatarId: Int) {
        try {
            // Actualiza en Room
            appDatabase.usuarioDao().actualizarAvatar(nombre, avatarId)

            // Actualiza en Firestore dentro de salones
            val salonesSnapshot = firestore.collection("salones").get().await()

            for (document in salonesSnapshot.documents) {
                val alumnos = document.get("alumnos") as? List<Map<String, Any>> ?: continue

                val index = alumnos.indexOfFirst { it["usuario"] == nombre }
                if (index != -1) {
                    val nuevosAlumnos = alumnos.toMutableList()
                    val alumnoModificado = nuevosAlumnos[index].toMutableMap()

                    // Agrega o actualiza avatarId
                    alumnoModificado["avatarId"] = avatarId
                    nuevosAlumnos[index] = alumnoModificado

                    firestore.collection("salones").document(document.id)
                        .update("alumnos", nuevosAlumnos)
                        .await()

                    break // Ya lo actualizamos, no hace falta seguir
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    suspend fun verificarCredenciales(usuario: String, clave: String): ResultadoLogin {
        fun Any?.toBooleanSafe(): Boolean = when (this) {
            is Boolean -> this
            is String -> this.toBooleanStrictOrNull() ?: this.equals("true", ignoreCase = true)
            is Number -> this.toInt() != 0
            else -> false
        }

        return try {
            val salonesSnapshot = firestore.collection("salones").get().await()

            for (document in salonesSnapshot.documents) {
                val alumnos = document.get("alumnos") as? List<Map<String, Any?>> ?: continue

                for (alumno in alumnos) {
                    val user = alumno["usuario"] as? String ?: continue
                    val pass = alumno["clave"] as? String ?: continue
                    val claveCambiada = alumno["claveCambiada"].toBooleanSafe()
                    val avatarId = (alumno["avatarId"] as? Long)?.toInt() ?: -1

                    // DEBUG opcional
                    Log.d("SyncRepository", "user=$user claveCambiada=$claveCambiada (${alumno["claveCambiada"]?.javaClass?.name})")

                    if (user == usuario && pass == clave) {
                        return ResultadoLogin(
                            esValido = true,
                            requiereCambioClave = !claveCambiada,
                            usuarioFirestore = user,
                            claveFirestore = pass,
                            avatarId = avatarId
                        )
                    }
                }
            }

            // No encontrado
            ResultadoLogin(false, false)
        } catch (e: Exception) {
            e.printStackTrace()
            ResultadoLogin(false, false)
        }
    }



    data class ResultadoLogin(
        val esValido: Boolean,
        val requiereCambioClave: Boolean,
        val usuarioFirestore: String? = null,
        val claveFirestore: String? = null,
        val avatarId: Int = -1 // ✅ AGREGAR ESTO
    )



    suspend fun actualizarCredencialesEnSalones(usuarioAntiguo: String, nuevaClave: String, nuevoUsuario: String): Boolean {
        return try {
            val db = FirebaseFirestore.getInstance()
            val salonesSnapshot = db.collection("salones").get().await()

            for (document in salonesSnapshot.documents) {
                val alumnos = document.get("alumnos") as? List<Map<String, Any>> ?: continue

                val index = alumnos.indexOfFirst { it["usuario"] == usuarioAntiguo }
                if (index != -1) {
                    val nuevosAlumnos = alumnos.toMutableList()
                    val alumnoModificado = nuevosAlumnos[index].toMutableMap()
                    alumnoModificado["usuario"] = nuevoUsuario
                    alumnoModificado["clave"] = nuevaClave
                    alumnoModificado["claveCambiada"] = true

                    nuevosAlumnos[index] = alumnoModificado

                    db.collection("salones").document(document.id)
                        .update("alumnos", nuevosAlumnos)
                        .await()

                    return true
                }
            }

            false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }





}