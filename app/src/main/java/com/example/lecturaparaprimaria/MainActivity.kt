package com.example.lecturaparaprimaria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.example.lecturaparaprimaria.data.AppDatabase
import com.example.lecturaparaprimaria.data.DatabaseInitializer // Asegúrate de importar esta clase
import com.example.lecturaparaprimaria.data.SyncRepository

import com.example.lecturaparaprimaria.ui.navigation.NavGraph
import com.example.lecturaparaprimaria.ui.theme.LecturaParaPrimariaTheme
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        val database = AppDatabase.getDatabase(applicationContext)

        // Inicializa los contenidos (Nivel 1) al iniciar la app
        lifecycleScope.launch {
            DatabaseInitializer.inicializarContenidos(database) // <-- Aquí se inserta el cuento y preguntas
        }

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