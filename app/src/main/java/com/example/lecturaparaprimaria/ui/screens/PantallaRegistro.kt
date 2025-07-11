package com.example.lecturaparaprimaria.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.lecturaparaprimaria.R
import com.example.lecturaparaprimaria.data.AppDatabase
import com.example.lecturaparaprimaria.data.Usuario
import com.example.lecturaparaprimaria.utils.PreferenceHelper
import com.example.lecturaparaprimaria.utils.SoundPlayer
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun PantallaRegistro(
    database: AppDatabase,
    onUsuarioRegistrado: (Usuario) -> Unit,
    irAPantallaUsuarios: () -> Unit
) {
    // Configuración del reproductor de sonido
    val context = LocalContext.current  // ✅ CORREGIDO AQUÍ
    val soundPlayer = remember { SoundPlayer(context) }  // ✅ AHORA FUNCIONA BIEN
    val sonidoRegistro = R.raw.sonido1
    val sonidoSeleccionUsuario = R.raw.sonido2

    // Limpieza de recursos al desmontar
    DisposableEffect(Unit) {
        onDispose { soundPlayer.release() }
    }

    var nombre by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.PersonAdd,
                contentDescription = "Registro",
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Crear nuevo usuario",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre de usuario") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de Registro con sonido
            Button(
                onClick = {
                    soundPlayer.playSound(sonidoRegistro)
                    if (nombre.isNotBlank()) {
                        coroutineScope.launch {
                            val usuario = Usuario(nombre)
                            database.usuarioDao().insertar(usuario)
                            try {
                                FirebaseFirestore.getInstance()
                                    .collection("usuarios")
                                    .document(usuario.nombre)
                                    .set(usuario)
                                    .await()
                                mensaje = "¡Usuario creado con éxito!"
                                PreferenceHelper.setUsuarioActual(context, usuario.nombre)
                                onUsuarioRegistrado(usuario)
                            } catch (e: Exception) {
                                mensaje = "Usuario guardado localmente"
                                PreferenceHelper.setUsuarioActual(context, usuario.nombre)
                                onUsuarioRegistrado(usuario)
                            }
                        }
                    } else {
                        mensaje = "Ingresa un nombre válido"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Registrarse", style = MaterialTheme.typography.labelLarge)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de Selección de Usuario con sonido diferente
            TextButton(
                onClick = {
                    soundPlayer.playSound(sonidoSeleccionUsuario)
                    irAPantallaUsuarios()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "¿Ya tienes una cuenta? Selecciona usuario",
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            mensaje?.let {
                Text(
                    text = it,
                    color = if (it.contains("éxito")) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}