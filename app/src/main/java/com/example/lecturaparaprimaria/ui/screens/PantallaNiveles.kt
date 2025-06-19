package com.example.lecturaparaprimaria.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun PantallaNiveles(
    nivelDesbloqueado: Int = 1,
    onNivelSeleccionado: (Int) -> Unit
) {
    var mostrarMarco by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300)
        mostrarMarco = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        AnimatedVisibility(
            visible = mostrarMarco,
            enter = androidx.compose.animation.slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(600)
            )
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp),
                shadowElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Selecciona un Nivel",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(5),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.height(500.dp)
                    ) {
                        items((1..50).toList()) { nivel ->
                            val desbloqueado = nivel <= nivelDesbloqueado
                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .alpha(if (desbloqueado) 1f else 0.4f)
                                    .clickable(enabled = desbloqueado) { onNivelSeleccionado(nivel) },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Nivel\n${nivel.toString().padStart(2, '0')}",
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp
                                )
                                if (!desbloqueado) {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = "Bloqueado",
                                        tint = Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
