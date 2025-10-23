package dev.prateekthakur.spendo.domain.models

import kotlin.collections.component1
import kotlin.collections.component2

data class CategoryExpense(
    val type: ExpenseType,
    val total: Double
)

fun List<Expense>.categoryExpenses() : List<CategoryExpense> {
    return this.groupBy { it.type }.map { (k, v) ->
        CategoryExpense(type = k, total = v.map { it.amount }.reduceOrNull { a, b -> a + b } ?: 0.0)
    }
}