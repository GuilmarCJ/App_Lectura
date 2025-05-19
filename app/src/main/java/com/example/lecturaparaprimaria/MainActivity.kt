package com.example.lecturaparaprimaria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import com.example.lecturaparaprimaria.ui.theme.LecturaParaPrimariaTheme
import com.example.lecturaparaprimaria.data.AppDatabase
import com.example.lecturaparaprimaria.data.Usuario
import com.example.lecturaparaprimaria.data.UsuarioDao

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)

        setContent {
            LecturaParaPrimariaTheme {
                var pantallaActual by remember { mutableStateOf("registro") }
                var usuarioActivo by remember { mutableStateOf<Usuario?>(null) }

                when (pantallaActual) {
                    "registro" -> PantallaRegistro(
                        database = database,
                        onUsuarioRegistrado = {
                            usuarioActivo = it
                            pantallaActual = "principal"
                        },
                        irAPantallaUsuarios = { pantallaActual = "seleccionar" }
                    )

                    "seleccionar" -> PantallaSeleccionUsuario(
                        database = database,
                        onUsuarioSeleccionado = {
                            usuarioActivo = it
                            pantallaActual = "principal"
                        },
                        onBack = { pantallaActual = "registro" }
                    )

                    "principal" -> PantallaPrincipal(usuario = usuarioActivo!!)
                }
            }
        }
    }
}


@Composable
fun PantallaRegistro(
    database: AppDatabase,
    onUsuarioRegistrado: (Usuario) -> Unit,
    irAPantallaUsuarios: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Registro de usuario", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (nombre.isNotBlank()) {
                        coroutineScope.launch {
                            val usuario = Usuario(nombre)
                            val resultado = database.usuarioDao().insertar(usuario)
                            if (resultado != -1L) {
                                mensaje = "Usuario registrado con éxito"
                                onUsuarioRegistrado(usuario)
                            } else {
                                mensaje = "El usuario ya existe"
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarse")
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(onClick = { irAPantallaUsuarios() }) {
                Text("Seleccionar usuario existente")
            }

            Spacer(modifier = Modifier.height(24.dp))

            mensaje?.let {
                Text(text = it, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
fun PantallaSeleccionUsuario(
    database: AppDatabase,
    onUsuarioSeleccionado: (Usuario) -> Unit,
    onBack: () -> Unit
) {
    var listaUsuarios by remember { mutableStateOf<List<Usuario>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        coroutineScope.launch {
            listaUsuarios = database.usuarioDao().obtenerTodos()
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Selecciona tu usuario", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            listaUsuarios.forEach { usuario ->
                Button(
                    onClick = { onUsuarioSeleccionado(usuario) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(usuario.nombre)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(onClick = { onBack() }) {
                Text("Volver")
            }
        }
    }
}
@Composable
fun PantallaPrincipal(usuario: Usuario) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(32.dp)) {
            Text("¡Bienvenido, ${usuario.nombre}!", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Aquí continuarás donde lo dejaste...", style = MaterialTheme.typography.bodyLarge)
        }
    }
}


