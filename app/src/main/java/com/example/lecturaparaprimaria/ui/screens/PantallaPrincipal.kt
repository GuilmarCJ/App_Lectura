package com.example.lecturaparaprimaria.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lecturaparaprimaria.data.Usuario
import com.example.lecturaparaprimaria.ui.components.Avatares

@Composable
fun PantallaPrincipal(usuario: Usuario) {
    var mostrarNiveles by remember { mutableStateOf(false) }
    var nivelDesbloqueado by remember { mutableStateOf(1) } // Puedes cargar esto de la BD si lo necesitas

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(bottom = 32.dp)
                ) {
                    Avatares.obtenerDrawable(usuario.avatarId)?.let { avatarRes ->
                        Image(
                            painter = painterResource(id = avatarRes),
                            contentDescription = "Avatar de ${usuario.nombre}",
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(32.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Column {
                        Text(
                            text = usuario.nombre,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "Level $nivelDesbloqueado",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                val buttonColors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4B5BA6),
                    contentColor = Color.White
                )

                Button(
                    onClick = { mostrarNiveles = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = buttonColors
                ) {
                    Text("JUGAR")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { /* TODO: Acción RANGO ACTUAL */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = buttonColors
                ) {
                    Text("RANGO ACTUAL")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { /* TODO: Acción TIENDA */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = buttonColors
                ) {
                    Text("TIENDA")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { /* TODO: Acción OPCIONES */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = buttonColors
                ) {
                    Text("OPCIONES")
                }
            }

            if (mostrarNiveles) {
                PantallaNiveles(
                    nivelDesbloqueado = nivelDesbloqueado,
                    onNivelSeleccionado = { nivel ->
                        // Aquí puedes manejar la lógica de navegación
                        mostrarNiveles = false
                        nivelDesbloqueado = nivel
                        // TODO: Ir al juego, por ejemplo
                    }
                )
            }
        }
    }
}
