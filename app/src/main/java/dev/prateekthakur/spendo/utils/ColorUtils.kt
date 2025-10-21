package dev.prateekthakur.spendo.utils

import dev.prateekthakur.spendo.domain.models.ExpenseType
import dev.prateekthakur.spendo.ui.theme.expenseTypeAnonymous
import dev.prateekthakur.spendo.ui.theme.expenseTypeFood
import dev.prateekthakur.spendo.ui.theme.expenseTypeHousehold
import dev.prateekthakur.spendo.ui.theme.expenseTypeMovie
import dev.prateekthakur.spendo.ui.theme.expenseTypeTravel
import dev.prateekthakur.spendo.ui.theme.expenseTypeUtility

fun getColor(type: ExpenseType) = when(type) {
    ExpenseType.ANONYMOUS -> expenseTypeAnonymous
    ExpenseType.FOOD -> expenseTypeFood
    ExpenseType.UTILITY -> expenseTypeUtility
    ExpenseType.TRAVEL -> expenseTypeTravel
    ExpenseType.MOVIE -> expenseTypeMovie
    ExpenseType.HOUSEHOLD -> expenseTypeHousehold
}