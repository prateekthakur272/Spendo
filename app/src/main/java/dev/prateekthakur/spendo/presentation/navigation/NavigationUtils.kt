package dev.prateekthakur.spendo.presentation.navigation

import androidx.navigation.NavController

fun NavController.safePopBackStack() {
    if (this.previousBackStackEntry != null) {
        this.popBackStack()
    }
}