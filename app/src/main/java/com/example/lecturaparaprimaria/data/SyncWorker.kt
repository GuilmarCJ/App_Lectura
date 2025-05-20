package com.example.lecturaparaprimaria.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val database = AppDatabase.getDatabase(applicationContext)
            val syncRepo = SyncRepository(database)
            syncRepo.syncUsuarios()
            Result.success()
        } catch (e: Exception) {
            Result.retry() // Reintentar si falla
        }
    }
}