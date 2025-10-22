package dev.prateekthakur.spendo.utils

import dev.prateekthakur.spendo.domain.models.ExpenseType

fun extractExpenseType(message: String): ExpenseType {
    val text = message.lowercase()
    return when {
        listOf("food", "swiggy", "zomato").any { text.contains(it) } -> ExpenseType.FOOD
        listOf("electricity", "water", "bill", "utility").any { text.contains(it) } -> ExpenseType.UTILITY
        listOf("uber", "ola", "taxi", "bus", "train").any { text.contains(it) } -> ExpenseType.TRAVEL
        listOf("movie", "bookmyshow").any { text.contains(it) } -> ExpenseType.MOVIE
        listOf("amazon", "flipkart", "shopping").any { text.contains(it) } -> ExpenseType.SHOPPING
        listOf("rent", "house").any { text.contains(it) } -> ExpenseType.HOUSEHOLD
        else -> ExpenseType.ANONYMOUS
    }
}

fun extractExpenseAmount(message: String): Double? {
    val deductionKeywords = listOf("debited", "deducted", "withdrawn", "spent", "payment", "purchase")
    if (deductionKeywords.none { message.contains(it, ignoreCase = true) }) return null
    val regex = """(?:Rs|INR|₹)\s?([\d,]+(?:\.\d{1,2})?)""".toRegex(RegexOption.IGNORE_CASE)
    val match = regex.find(message) ?: return null
    return match.groupValues[1].replace(",", "").toDoubleOrNull()
}