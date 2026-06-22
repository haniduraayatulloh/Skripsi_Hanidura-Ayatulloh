package com.vrasmedia.desainujikeranjang.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.vrasmedia.desainujikeranjang.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    // Timer 3 detik untuk pindah halaman
    LaunchedEffect(Unit) {
        delay(3000)
        onTimeout()
    }

    // Cukup satu wadah gambar utuh yang memenuhi seluruh layar
    Image(
        painter = painterResource(id = R.drawable.img_splash_full), // Taruh file gambar utuh Anda di res/drawable
        contentDescription = "Splash Screen Alfagift",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop // Memastikan gambar memenuhi layar tanpa distorsi
    )
}