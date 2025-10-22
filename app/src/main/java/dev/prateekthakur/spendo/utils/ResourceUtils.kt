package dev.prateekthakur.spendo.utils

import androidx.compose.ui.graphics.Color
import dev.prateekthakur.spendo.R
import dev.prateekthakur.spendo.domain.models.ExpenseType
import dev.prateekthakur.spendo.ui.theme.expenseTypeAnonymous
import dev.prateekthakur.spendo.ui.theme.expenseTypeFood
import dev.prateekthakur.spendo.ui.theme.expenseTypeHousehold
import dev.prateekthakur.spendo.ui.theme.expenseTypeMovie
import dev.prateekthakur.spendo.ui.theme.expenseTypeTravel
import dev.prateekthakur.spendo.ui.theme.expenseTypeUtility

fun getColor(type: ExpenseType) : Color = when(type) {
    ExpenseType.ANONYMOUS -> expenseTypeAnonymous
    ExpenseType.FOOD -> expenseTypeFood
    ExpenseType.UTILITY -> expenseTypeUtility
    ExpenseType.TRAVEL -> expenseTypeTravel
    ExpenseType.MOVIE -> expenseTypeMovie
    ExpenseType.HOUSEHOLD -> expenseTypeHousehold
}

fun getIcon(type: ExpenseType) : Int = when(type){
    ExpenseType.ANONYMOUS -> R.drawable.rounded_unknown_med_24
    ExpenseType.FOOD -> R.drawable.rounded_fastfood_24
    ExpenseType.UTILITY -> R.drawable.rounded_mobile_hand_24
    ExpenseType.TRAVEL -> R.drawable.rounded_movie_24
    ExpenseType.MOVIE -> R.drawable.rounded_movie_24
    ExpenseType.HOUSEHOLD -> R.drawable.rounded_home_24
}