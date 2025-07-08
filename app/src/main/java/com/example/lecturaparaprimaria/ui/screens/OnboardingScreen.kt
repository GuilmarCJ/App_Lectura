package com.example.lecturaparaprimaria.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lecturaparaprimaria.R
import com.example.lecturaparaprimaria.utils.SoundPlayer
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

import androidx.compose.runtime.DisposableEffect
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    onStartClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Configuración del reproductor de sonido
    val soundPlayer = SoundPlayer(LocalContext.current)
    val clickSound = R.raw.click_sound  // Asegúrate de tener este archivo en res/raw

    // Función para manejar clicks con sonido
    fun handleClickWithSound(action: () -> Unit) {
        soundPlayer.playSound(clickSound)
        action()
    }

    // Lista de pautas de uso (texto e iconos)
    val pages = listOf(
        OnboardingPage(
            title = "Bienvenido a Lectura Primaria",
            description = "Una aplicación diseñada para mejorar las habilidades de lectura de niños de primaria",
            icon = Icons.Default.School
        ),
        OnboardingPage(
            title = "Registro de Usuario",
            description = "Crea un perfil con nombre y avatar para personalizar tu experiencia",
            icon = Icons.Default.Person
        ),
        OnboardingPage(
            title = "Unidades de Aprendizaje",
            description = "Explora las diferentes unidades con lecciones interactivas",
            icon = Icons.Default.Book
        ),
        OnboardingPage(
            title = "Progreso",
            description = "Sigue tu avance y mejora tus habilidades de lectura",
            icon = Icons.Default.Star
        )
    )

    // Estado del pager
    val pagerState = rememberPagerState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Carrusel horizontal con pautas
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = pages[page].icon,
                    contentDescription = pages[page].title,
                    modifier = Modifier.size(80.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = pages[page].title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = pages[page].description,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        }

        // Indicadores de página (puntos)
        Row(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pages.size) { iteration ->
                val color = if (pagerState.currentPage == iteration)
                    MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(color)
                )
            }
        }

        // Botón "Iniciar" (solo en la última página) con sonido
        if (pagerState.currentPage == pages.size - 1) {
            Button(
                onClick = { handleClickWithSound(onStartClicked) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 24.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Comenzar", style = MaterialTheme.typography.labelLarge)
            }
        }
    }

    // Limpieza cuando el composable se desmonte
    DisposableEffect(Unit) {
        onDispose {
            soundPlayer.release()
        }
    }
}

// Data class para las páginas de onboarding
data class OnboardingPage(
    val title: String,
    val description: String,
    val icon: ImageVector
)