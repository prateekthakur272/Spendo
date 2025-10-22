package dev.prateekthakur.spendo.domain.repository

import dev.prateekthakur.spendo.domain.models.Expense
import dev.prateekthakur.spendo.domain.models.ExpenseType
import dev.prateekthakur.spendo.domain.models.PeriodFilter
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun get(periodFilter: PeriodFilter? = null, typeFilter: ExpenseType? = null) : Flow<List<Expense>>
    suspend fun add(expense: Expense)
    suspend fun delete(expense: Expense)
    suspend fun deleteAll()
}