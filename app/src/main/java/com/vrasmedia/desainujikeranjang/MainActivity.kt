package com.vrasmedia.desainujikeranjang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vrasmedia.desainujikeranjang.ui.AppNavigation
import com.vrasmedia.desainujikeranjang.ui.theme.DesainUjiKeranjangTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesainUjiKeranjangTheme {
                // Cukup panggil navigasi utama di sini, sisanya diatur oleh NavHost
                AppNavigation()
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DesainUjiKeranjangTheme {
        Greeting("Android")
    }
}

@Composable
fun Greeting(x0: String) {
    TODO("Not yet implemented")
}