package dev.prateekthakur.spendo.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.prateekthakur.spendo.presentation.viewmodels.ExpenseViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(
    startDestination: String,
    navHostController: NavHostController = rememberNavController()
) {
    val expenseViewModel: ExpenseViewModel = koinViewModel()
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable("/") {
            HomeScreen(
                expenseViewModel = expenseViewModel,
                navHostController = navHostController
            )
        }
        composable("/create") {
            CreateExpenseScreen(
                expenseViewModel = expenseViewModel,
                navHostController = navHostController
            )
        }
    }
}