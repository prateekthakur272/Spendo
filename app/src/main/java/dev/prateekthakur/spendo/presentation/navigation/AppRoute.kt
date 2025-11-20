package dev.prateekthakur.spendo.presentation.navigation

import dev.prateekthakur.spendo.domain.models.ExpenseType
import dev.prateekthakur.spendo.domain.models.PeriodFilter
import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoute {
    @Serializable
    data object Home : AppRoute()
    @Serializable
    data object CreateExpense : AppRoute()
    @Serializable
    data object Settings : AppRoute()
    @Serializable
    data class Expenses(
        val type: ExpenseType? = null,
        val periodFilter: PeriodFilter? = null
    ) : AppRoute()
}