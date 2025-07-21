package com.example.lecturaparaprimaria.data

import androidx.work.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit

class SyncRepository(private val appDatabase: AppDatabase) {
    private val firestore = FirebaseFirestore.getInstance()
    private val usuariosCollection = firestore.collection("usuarios")

    suspend fun syncUsuarios() {
        try {
            val usuariosLocales = appDatabase.usuarioDao().obtenerTodos()

            usuariosLocales.forEach { usuario ->
                val usuarioRef = usuariosCollection.document(usuario.nombre)
                val usuarioFirebase = usuarioRef.get().await()

                if (!usuarioFirebase.exists() ||
                    usuarioFirebase.getLong("avatarId")?.toInt() != usuario.avatarId) {
                    // Guardamos solo el ID lógico
                    usuarioRef.set(usuario).await()
                }
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


    suspend fun verificarCredenciales(usuario: String, clave: String): Boolean {
        return try {
            val salonesSnapshot = firestore.collection("salones").get().await()

            for (document in salonesSnapshot.documents) {
                val alumnos = document.get("alumnos") as? List<Map<String, Any>> ?: continue

                for (alumno in alumnos) {
                    val user = alumno["usuario"] as? String
                    val pass = alumno["clave"] as? String
                    if (user == usuario && pass == clave) {
                        return true // ¡Coincidencia encontrada!
                    }
                }
            }

            false // No encontrado
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun actualizarCredencialesEnSalones(usuarioAntiguo: String, nuevaClave: String, nuevoUsuario: String): Boolean {
        return try {
            val db = FirebaseFirestore.getInstance()
            val salonesSnapshot = db.collection("salones").get().await()

            for (document in salonesSnapshot.documents) {
                val alumnos = document.get("alumnos") as? List<Map<String, String>> ?: continue

                // Verifica si el alumno actual está en este documento
                val index = alumnos.indexOfFirst { it["usuario"] == usuarioAntiguo }
                if (index != -1) {
                    // Modifica los datos
                    val nuevosAlumnos = alumnos.toMutableList()
                    val alumnoModificado = nuevosAlumnos[index].toMutableMap()
                    alumnoModificado["usuario"] = nuevoUsuario
                    alumnoModificado["clave"] = nuevaClave
                    nuevosAlumnos[index] = alumnoModificado

                    // Sobrescribe todo el array
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