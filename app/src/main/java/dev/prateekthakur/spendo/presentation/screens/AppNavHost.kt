package dev.prateekthakur.spendo.presentation.screens

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import dev.prateekthakur.spendo.domain.models.ExpenseType
import dev.prateekthakur.spendo.domain.models.PeriodFilter
import dev.prateekthakur.spendo.presentation.viewmodels.ExpenseViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AppNavHost(
    startDestination: String,
    navHostController: NavHostController = rememberNavController()
) {
    val smsPermissionState = rememberPermissionState(permission = Manifest.permission.RECEIVE_SMS)

    LaunchedEffect(Unit) {
        smsPermissionState.launchPermissionRequest()
    }

    NavHost(navController = navHostController, startDestination = startDestination) {
        composable("/") {
            val expenseViewModel: ExpenseViewModel = koinViewModel()
            HomeScreen(
                expenseViewModel = expenseViewModel,
                navHostController = navHostController
            )
        }
        composable("/create") {
            val expenseViewModel: ExpenseViewModel = koinViewModel()
            CreateExpenseScreen(
                expenseViewModel = expenseViewModel,
                navHostController = navHostController
            )
        }
        composable("/expenses?type={type}&periodFilter={periodFilter}") {
            val expenseViewModel: ExpenseViewModel = koinViewModel()
            val type = it.arguments?.getString("type")
            val periodFilter = it.arguments?.getString("periodFilter")
            ExpensesScreen(
                expenseViewModel = expenseViewModel,
                navHostController = navHostController,
                typeFilter = type?.runCatching { ExpenseType.valueOf(type) }?.getOrNull(),
                periodFilter = periodFilter?.runCatching { PeriodFilter.valueOf(periodFilter) }?.getOrNull()
            )
        }
        composable("/settings") {
            val expenseViewModel: ExpenseViewModel = koinViewModel()
            SettingsScreen(expenseViewModel = expenseViewModel, navHostController = navHostController)
        }
    }
}

fun NavController.safePopBackStack() {
    if (this.previousBackStackEntry != null) {
        this.popBackStack()
    }
}