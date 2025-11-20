package dev.prateekthakur.spendo.presentation.navigation

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import dev.prateekthakur.spendo.presentation.screens.CreateExpenseScreen
import dev.prateekthakur.spendo.presentation.screens.ExpensesScreen
import dev.prateekthakur.spendo.presentation.screens.HomeScreen
import dev.prateekthakur.spendo.presentation.screens.SettingsScreen
import dev.prateekthakur.spendo.presentation.viewmodels.ExpenseViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AppNavHost(
    startDestination: AppRoute,
    navHostController: NavHostController = rememberNavController()
) {
    val smsPermissionState = rememberPermissionState(permission = Manifest.permission.RECEIVE_SMS)

    LaunchedEffect(Unit) {
        smsPermissionState.launchPermissionRequest()
    }

    NavHost(navController = navHostController, startDestination = startDestination) {
        composable<AppRoute.Home> {
            val expenseViewModel: ExpenseViewModel = koinViewModel()
            HomeScreen(
                expenseViewModel = expenseViewModel,
                navHostController = navHostController
            )
        }
        composable<AppRoute.CreateExpense> {
            val expenseViewModel: ExpenseViewModel = koinViewModel()
            CreateExpenseScreen(
                expenseViewModel = expenseViewModel,
                navHostController = navHostController
            )
        }
        composable<AppRoute.Expenses> {
            val expenseViewModel: ExpenseViewModel = koinViewModel()
            val route = it.toRoute<AppRoute.Expenses>()
            ExpensesScreen(
                expenseViewModel = expenseViewModel,
                navHostController = navHostController,
                typeFilter = route.type,
                periodFilter = route.periodFilter
            )
        }
        composable<AppRoute.Settings> {
            val expenseViewModel: ExpenseViewModel = koinViewModel()
            SettingsScreen(
                expenseViewModel = expenseViewModel,
                navHostController = navHostController
            )
        }
    }
}