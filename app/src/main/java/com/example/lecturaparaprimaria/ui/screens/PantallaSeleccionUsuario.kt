package com.example.lecturaparaprimaria.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.lecturaparaprimaria.data.AppDatabase
import com.example.lecturaparaprimaria.data.Usuario
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun PantallaSeleccionUsuario(
    database: AppDatabase,
    onUsuarioSeleccionado: (Usuario) -> Unit,
    onBack: () -> Unit
) {
    var listaUsuarios by remember { mutableStateOf<List<Usuario>>(emptyList()) }
    var mostrarDialogoConfirmacion by remember { mutableStateOf(false) }
    var usuarioAEliminar by remember { mutableStateOf<Usuario?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    // Función para cargar usuarios
    fun cargarUsuarios() {
        coroutineScope.launch {
            listaUsuarios = database.usuarioDao().obtenerTodos()
        }
    }

    // Cargar usuarios al iniciar
    LaunchedEffect(true) {
        cargarUsuarios()
    }

    // Función para eliminar usuario
    fun eliminarUsuario(usuario: Usuario) {
        coroutineScope.launch {
            try {
                // Eliminar de Room
                database.usuarioDao().eliminar(usuario)

                // Eliminar de Firebase
                FirebaseFirestore.getInstance()
                    .collection("usuarios")
                    .document(usuario.nombre)
                    .delete()
                    .await()

                // Mostrar confirmación
                Toast.makeText(
                    context,
                    "Usuario ${usuario.nombre} eliminado",
                    Toast.LENGTH_SHORT
                ).show()

                // Recargar lista
                cargarUsuarios()
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    "Error al eliminar: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // Diálogo de confirmación
    if (mostrarDialogoConfirmacion) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoConfirmacion = false },
            title = { Text("Confirmar eliminación") },
            text = { Text("¿Estás seguro de eliminar a ${usuarioAEliminar?.nombre}?") },
            confirmButton = {
                Button(
                    onClick = {
                        usuarioAEliminar?.let { eliminarUsuario(it) }
                        mostrarDialogoConfirmacion = false
                    }
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogoConfirmacion = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Selecciona tu usuario",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (listaUsuarios.isEmpty()) {
                Text(
                    text = "No hay usuarios registrados",
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                listaUsuarios.forEach { usuario ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = usuario.nombre,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                if (usuario.avatarId != -1) {
                                    Text(
                                        text = "Avatar: ${usuario.avatarId}",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }

                            // Botón de seleccionar
                            Button(
                                onClick = { onUsuarioSeleccionado(usuario) },
                                modifier = Modifier.padding(end = 4.dp)
                            ) {
                                Text("Seleccionar")
                            }

                            // Botón de eliminar
                            IconButton(
                                onClick = {
                                    usuarioAEliminar = usuario
                                    mostrarDialogoConfirmacion = true
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Eliminar usuario"
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                onClick = { onBack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver")
            }
        }
    }
}