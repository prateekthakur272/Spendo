package dev.prateekthakur.spendo.presentation.viewmodels

import dev.prateekthakur.spendo.domain.models.Expense
import dev.prateekthakur.spendo.domain.models.ExpenseType
import dev.prateekthakur.spendo.domain.models.PeriodFilter

sealed class ExpenseIntent {
    data class Get(
        val periodFilter: PeriodFilter? = null,
        val typeFilter: ExpenseType? = null
    ) : ExpenseIntent()
    data class Create(val expense: Expense) : ExpenseIntent()
    data class Delete(val expense: Expense) : ExpenseIntent()
    data object Clear : ExpenseIntent()
}