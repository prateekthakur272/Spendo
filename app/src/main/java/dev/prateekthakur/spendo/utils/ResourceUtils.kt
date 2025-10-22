package dev.prateekthakur.spendo.utils

import androidx.compose.ui.graphics.Color
import dev.prateekthakur.spendo.R
import dev.prateekthakur.spendo.domain.models.ExpenseType
import dev.prateekthakur.spendo.presentation.theme.expenseTypeAnonymous
import dev.prateekthakur.spendo.presentation.theme.expenseTypeFood
import dev.prateekthakur.spendo.presentation.theme.expenseTypeHousehold
import dev.prateekthakur.spendo.presentation.theme.expenseTypeMovie
import dev.prateekthakur.spendo.presentation.theme.expenseTypeShopping
import dev.prateekthakur.spendo.presentation.theme.expenseTypeTravel
import dev.prateekthakur.spendo.presentation.theme.expenseTypeUtility

fun getColor(type: ExpenseType) : Color = when(type) {
    ExpenseType.ANONYMOUS -> expenseTypeAnonymous
    ExpenseType.FOOD -> expenseTypeFood
    ExpenseType.UTILITY -> expenseTypeUtility
    ExpenseType.TRAVEL -> expenseTypeTravel
    ExpenseType.MOVIE -> expenseTypeMovie
    ExpenseType.HOUSEHOLD -> expenseTypeHousehold
    ExpenseType.SHOPPING -> expenseTypeShopping
}

fun getIcon(type: ExpenseType) : Int = when(type){
    ExpenseType.ANONYMOUS -> R.drawable.rounded_unknown_med_24
    ExpenseType.FOOD -> R.drawable.rounded_fastfood_24
    ExpenseType.UTILITY -> R.drawable.rounded_mobile_hand_24
    ExpenseType.TRAVEL -> R.drawable.rounded_movie_24
    ExpenseType.MOVIE -> R.drawable.rounded_movie_24
    ExpenseType.HOUSEHOLD -> R.drawable.rounded_home_24
    ExpenseType.SHOPPING -> R.drawable.rounded_shopping_cart_24
}