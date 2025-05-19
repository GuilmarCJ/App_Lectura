package com.example.lecturaparaprimaria.data

import androidx.work.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit

class SyncRepository(private val appDatabase: AppDatabase) {
    private val firestore = FirebaseFirestore.getInstance()
    private val usuariosCollection = firestore.collection("usuarios")

    // Sincronizar datos locales con Firebase
    suspend fun syncUsuarios() {
        try {
            // 1. Obtener usuarios locales (Room)
            val usuariosLocales = appDatabase.usuarioDao().obtenerTodos()

            // 2. Subir cada usuario a Firebase (si no existe)
            usuariosLocales.forEach { usuario ->
                val usuarioRef = usuariosCollection.document(usuario.nombre)
                val usuarioFirebase = usuarioRef.get().await()

                if (!usuarioFirebase.exists()) {
                    usuarioRef.set(usuario).await()
                }
            }

            // 3. Opcional: Descargar usuarios de Firebase para actualizar Room
            // (Si necesitas que los cambios en la nube sobrescriban lo local)
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
}