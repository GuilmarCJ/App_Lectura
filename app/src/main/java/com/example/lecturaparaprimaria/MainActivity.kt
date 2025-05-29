package com.example.lecturaparaprimaria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.rememberCoroutineScope
import com.example.lecturaparaprimaria.data.AppDatabase
import com.example.lecturaparaprimaria.data.SyncRepository
import com.example.lecturaparaprimaria.ui.navigation.NavGraph
import com.example.lecturaparaprimaria.ui.theme.LecturaParaPrimariaTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        val database = AppDatabase.getDatabase(applicationContext)
        val syncRepo = SyncRepository(database)
        syncRepo.scheduleSync()

        setContent {
            LecturaParaPrimariaTheme {
                val coroutineScope = rememberCoroutineScope()
                NavGraph(
                    database = database,
                    coroutineScope = coroutineScope
                )
            }
        }
    }
}