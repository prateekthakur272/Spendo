package dev.prateekthakur.spendo.presentation.navigation

import androidx.navigation.NavHostController

fun NavHostController.safePopBackStack() {
    if (this.previousBackStackEntry != null) {
        this.popBackStack()
    }
}