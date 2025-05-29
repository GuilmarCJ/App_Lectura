package com.example.lecturaparaprimaria.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lecturaparaprimaria.data.Usuario
import com.example.lecturaparaprimaria.ui.components.Avatares

@Composable
fun PantallaPrincipal(usuario: Usuario) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Header con avatar y nombre
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // Avatar
                Avatares.obtenerDrawable(usuario.avatarId)?.let { avatarRes ->
                    Image(
                        painter = painterResource(id = avatarRes),
                        contentDescription = "Avatar de ${usuario.nombre}",
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Nombre y nivel
                Column {
                    Text(
                        text = usuario.nombre,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Level 1", // Puedes hacer esto dinámico si lo necesitas
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // Fecha y hora
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "TODAY",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "22 minutes", // Puedes hacer esto dinámico
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }

            // Aquí iría el contenido principal de tu pantalla
            // (Lecciones, progreso, etc.)

            // Ejemplo de sección de progreso (puedes personalizarlo)
            Text(
                text = "Tu progreso",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
            )

            // Puedes agregar aquí tus componentes específicos
            // como gráficos de progreso, listas de lecciones, etc.
        }
    }
}

