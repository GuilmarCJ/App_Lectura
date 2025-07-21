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
    onLoginSuccess: (Usuario) -> Unit,
    onBack: () -> Unit,
    syncRepository: SyncRepository, // <-- nuevo
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
            label = { Text("Correo electrónico") },
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
                        val credencialesValidas = syncRepository.verificarCredenciales(email, password)
                        cargando = false

                        if (credencialesValidas) {
                            val nombre = email.substringBefore('@')
                            var usuario = database.usuarioDao().obtenerPorNombre(nombre)
                            if (usuario == null) {
                                val nuevo = Usuario(nombre = nombre, avatarId = -1)
                                database.usuarioDao().insertar(nuevo)
                                usuario = nuevo
                            }
                            onLoginSuccess(usuario)
                        } else {
                            Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
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
