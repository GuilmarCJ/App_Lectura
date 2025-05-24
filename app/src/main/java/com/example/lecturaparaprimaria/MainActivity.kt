package com.example.lecturaparaprimaria

import android.os.Bundle
import android.widget.Toast
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
import com.example.lecturaparaprimaria.data.SyncRepository
import com.example.lecturaparaprimaria.data.Usuario
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

//avatares
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.coroutineScope

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.launch


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.compose.foundation.layout.Row


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        val database = AppDatabase.getDatabase(applicationContext)
        val syncRepo = SyncRepository(database)
        syncRepo.scheduleSync()

        setContent {
            LecturaParaPrimariaTheme {
                // Creamos un CoroutineScope que se limpie automáticamente
                val coroutineScope = rememberCoroutineScope()

                var pantallaActual by remember { mutableStateOf("registro") }
                var usuarioActivo by remember { mutableStateOf<Usuario?>(null) }

                // Función para refrescar el usuario
                suspend fun refrescarUsuario(nombre: String) {
                    usuarioActivo = database.usuarioDao().obtenerPorNombre(nombre)
                }

                when (pantallaActual) {
                    "registro" -> PantallaRegistro(
                        database = database,
                        onUsuarioRegistrado = { usuario ->
                            usuarioActivo = usuario
                            pantallaActual = "seleccionar_avatar"
                        },
                        irAPantallaUsuarios = { pantallaActual = "seleccionar" }
                    )

                    "seleccionar" -> PantallaSeleccionUsuario(
                        database = database,
                        onUsuarioSeleccionado = { usuario ->
                            usuarioActivo = usuario
                            pantallaActual = if (usuario.avatarId == -1) "seleccionar_avatar" else "principal"
                        },
                        onBack = { pantallaActual = "registro" }
                    )

                    "seleccionar_avatar" -> PantallaSeleccionAvatar(
                        usuario = usuarioActivo!!,
                        database = database,
                        onAvatarSeleccionado = {
                            // Usamos el coroutineScope creado arriba
                            coroutineScope.launch {
                                refrescarUsuario(usuarioActivo!!.nombre)
                                pantallaActual = "principal"
                            }
                        }
                    )

                    "principal" -> {
                        // Actualizamos al entrar a principal
                        LaunchedEffect(Unit) {
                            if (usuarioActivo != null) {
                                refrescarUsuario(usuarioActivo!!.nombre)
                            }
                        }
                        PantallaPrincipal(usuario = usuarioActivo!!)
                    }
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
                            // 1. Guardar en Room
                            database.usuarioDao().insertar(usuario)

                            // 2. Sincronizar con Firebase
                            try {
                                val firestore = FirebaseFirestore.getInstance()
                                firestore.collection("usuarios")
                                    .document(usuario.nombre)
                                    .set(usuario)
                                    .await()

                                mensaje = "Usuario registrado correctamente"
                                onUsuarioRegistrado(usuario) // Esto ahora redirige a selección de avatar
                            } catch (e: Exception) {
                                mensaje = "Usuario registrado localmente (sin conexión)"
                                onUsuarioRegistrado(usuario) // Redirige igualmente
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarse")
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = { irAPantallaUsuarios() },
                modifier = Modifier.fillMaxWidth()
            ) {
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




@Composable
fun PantallaPrincipal(usuario: Usuario) {

    LaunchedEffect(usuario) {
        println("DEBUG - Datos del usuario en PantallaPrincipal:")
        println("Nombre: ${usuario.nombre}")
        println("Avatar ID: ${usuario.avatarId}")
        println("Drawable correspondiente: ${Avatares.obtenerDrawable(usuario.avatarId)}")
    }

    // Verificamos si tenemos un avatar válido
    val avatarDrawable = if (usuario.avatarId > -1) {
        Avatares.obtenerDrawable(usuario.avatarId)
    } else {
        null
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Mostramos avatar si existe
            avatarDrawable?.let { drawable ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = drawable),
                        contentDescription = "Avatar de ${usuario.nombre}",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .border(
                                width = 3.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = CircleShape
                            ),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    Column {
                        Text(
                            text = "¡Bienvenido,",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = usuario.nombre,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            } ?: run {
                // Mostramos solo el nombre si no hay avatar
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "¡Bienvenido,",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = usuario.nombre,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Aquí continuarás donde lo dejaste...",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}



@Composable
fun PantallaSeleccionAvatar(
    usuario: Usuario,
    database: AppDatabase,
    onAvatarSeleccionado: () -> Unit
) {
    val avatares = Avatares.lista
    // Inicializamos con el ID lógico del avatar
    var idAvatarSeleccionado by remember { mutableIntStateOf(usuario.avatarId) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Selecciona tu avatar",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(avatares) { (idLogico, drawable) ->
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(
                            width = if (idAvatarSeleccionado == idLogico) 4.dp else 0.dp,
                            color = if (idAvatarSeleccionado == idLogico) MaterialTheme.colorScheme.primary
                            else Color.Transparent,
                            shape = CircleShape
                        )
                        .clickable { idAvatarSeleccionado = idLogico }
                ) {
                    Image(
                        painter = painterResource(id = drawable),
                        contentDescription = "Avatar $idLogico",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    // Guardamos solo el ID lógico (12, 16, etc.)
                    database.usuarioDao().actualizarAvatar(usuario.nombre, idAvatarSeleccionado)

                    try {
                        FirebaseFirestore.getInstance()
                            .collection("usuarios")
                            .document(usuario.nombre)
                            .update("avatarId", idAvatarSeleccionado)
                            .await()
                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            "Avatar guardado localmente. Se sincronizará con internet",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    onAvatarSeleccionado()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            enabled = idAvatarSeleccionado != -1
        ) {
            Text("Continuar")
        }
    }
}