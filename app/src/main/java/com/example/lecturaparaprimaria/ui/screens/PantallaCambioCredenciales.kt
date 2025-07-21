package com.example.lecturaparaprimaria.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.lecturaparaprimaria.data.AppDatabase
import com.example.lecturaparaprimaria.data.SyncRepository
import com.example.lecturaparaprimaria.data.Usuario
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun PantallaCambioCredenciales(
    usuarioActual: String,
    claveActual: String,
    syncRepository: SyncRepository,
    onCredencialesActualizadas: (String) -> Unit
) {
    var nuevoUsuario by remember { mutableStateOf("") }
    var nuevaClave by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Actualizar Usuario y Clave", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nuevoUsuario,
            onValueChange = { nuevoUsuario = it },
            label = { Text("Nuevo Usuario (Tu nombre)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = nuevaClave,
            onValueChange = { nuevaClave = it },
            label = { Text("Nueva Clave") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (nuevoUsuario.isBlank() || nuevaClave.isBlank()) {
                mensajeError = "Todos los campos son obligatorios"
                return@Button
            }

            scope.launch {
                val actualizado = syncRepository.actualizarCredencialesEnSalones(
                    usuarioAntiguo = usuarioActual,
                    nuevoUsuario = nuevoUsuario,
                    nuevaClave = nuevaClave
                )
                if (actualizado) {
                    onCredencialesActualizadas(nuevoUsuario)
                } else {
                    mensajeError = "Error actualizando las credenciales"
                }
            }
        }) {
            Text("Guardar Cambios")
        }


        mensajeError?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colors.error)
        }
    }
}
