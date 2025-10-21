package dev.prateekthakur.spendo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.prateekthakur.spendo.presentation.screens.AppNavHost
import dev.prateekthakur.spendo.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                AppNavHost(startDestination = "/expenses?type=FOOD")
            }
        }
    }
}