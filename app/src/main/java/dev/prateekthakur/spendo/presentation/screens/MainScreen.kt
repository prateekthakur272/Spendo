package dev.prateekthakur.spendo.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.prateekthakur.spendo.R
import org.koin.androidx.compose.koinViewModel

enum class MainScreenDestinations(
    val label: String,
    val icon: Int,
    val selectedIcon: Int,
    val route: String
) {
    OVERVIEW("Overview", R.drawable.chart_, R.drawable.chart_filled, "/overview"),
    CALENDAR("Calendar", R.drawable.calendar, R.drawable.calendar_filled, "/calendar"),
    HOME("Home", R.drawable.home, R.drawable.home_filled, "/home"),
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar(tonalElevation = 0.dp) {
                MainScreenDestinations.entries.forEach { destination ->
                    val selected = currentRoute == destination.route
                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(
                                    id = if (selected) destination.selectedIcon else destination.icon
                                ),
                                contentDescription = destination.label,
                                modifier = Modifier.size(28.dp)
                            )
                        },
                        label = null
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainScreenDestinations.OVERVIEW.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(MainScreenDestinations.OVERVIEW.route) {
                AllExpensesScreen(expenseViewModel = koinViewModel())
            }
            composable(MainScreenDestinations.CALENDAR.route) {}
            composable(MainScreenDestinations.HOME.route) {}
        }
    }
}