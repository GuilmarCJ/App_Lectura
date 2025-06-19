package com.example.lecturaparaprimaria.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lecturaparaprimaria.data.AppDatabase
import com.example.lecturaparaprimaria.data.Usuario
import com.example.lecturaparaprimaria.ui.components.Avatares
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun PantallaSeleccionAvatar(
    usuario: Usuario,
    database: AppDatabase,
    onAvatarSeleccionado: () -> Unit
) {
    val avatares = Avatares.lista
    val avataresDesbloqueados = listOf(16, 17, 18, 19) // IDs lógicos desbloqueados

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
                val desbloqueado = idLogico in avataresDesbloqueados

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
                        .then(if (desbloqueado) Modifier.clickable { idAvatarSeleccionado = idLogico } else Modifier)
                ) {
                    Image(
                        painter = painterResource(id = drawable),
                        contentDescription = "Avatar $idLogico",
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(if (desbloqueado) 1f else 0.4f),
                        contentScale = ContentScale.Crop
                    )
                    if (!desbloqueado) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Bloqueado",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(32.dp),
                            tint = Color.White
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                coroutineScope.launch {
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
