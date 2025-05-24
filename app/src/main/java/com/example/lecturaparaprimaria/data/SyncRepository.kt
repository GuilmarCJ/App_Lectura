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

            // Sincroniza con Firebase
            firestore.collection("usuarios")
                .document(nombre)
                .update("avatarId", avatarId)
                .await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}