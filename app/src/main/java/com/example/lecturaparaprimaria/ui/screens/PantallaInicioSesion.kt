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
fun PantallaInicioSesion(
    database: AppDatabase,
    syncRepository: SyncRepository,
    onLoginSuccess: (Usuario, Boolean) -> Unit,
    onRequirePasswordChange: (String, String) -> Unit, // 👈 NUEVO
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var cargando by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Iniciar Sesión", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Usuario") }, // si no usas correo real, cambia el label
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (cargando) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = {
                    cargando = true
                    scope.launch {
                        val resultado = syncRepository.verificarCredenciales(email, password)
                        cargando = false

                        if (resultado.esValido) {
                            val nombre = resultado.usuarioFirestore ?: email.substringBefore('@')
                            val avatarId = resultado.avatarId

                            var usuario = database.usuarioDao().obtenerPorNombre(nombre)

                            if (usuario == null) {
                                usuario = Usuario(nombre = nombre, avatarId = avatarId)
                                database.usuarioDao().insertar(usuario)
                            } else if (usuario.avatarId != avatarId && avatarId != -1) {
                                // actualiza por si acaso
                                database.usuarioDao().actualizarAvatar(nombre, avatarId)
                                usuario = usuario.copy(avatarId = avatarId)
                            }


                            if (resultado.requiereCambioClave) {
                                onRequirePasswordChange(nombre, password)
                            } else {
                                onLoginSuccess(usuario, false)
                            }
                        }


                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar Sesión")
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
                Text("Volver")
            }
        }
    }
}

