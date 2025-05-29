package com.example.lecturaparaprimaria.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lecturaparaprimaria.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

import androidx.compose.foundation.background

import androidx.compose.ui.draw.clip


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    onStartClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    // 1. Lista de imágenes (asegúrate de que estos recursos existen en res/drawable)
    val pages = listOf(
        R.drawable.onboarding1,
        R.drawable.onboarding2,
        R.drawable.onboarding3,
        R.drawable.onboarding4
    )

    // 2. Estado del pager
    val pagerState = rememberPagerState()

    Column(modifier = modifier.fillMaxSize()) {
        // 3. Carrusel horizontal
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = pages[page]),
                    contentDescription = "Página ${page + 1} de introducción",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // 4. Indicadores de página (puntos)
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

        // 5. Botón "Iniciar" (solo en la última página)
        if (pagerState.currentPage == pages.size - 1) {
            Button(
                onClick = onStartClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Iniciar", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}