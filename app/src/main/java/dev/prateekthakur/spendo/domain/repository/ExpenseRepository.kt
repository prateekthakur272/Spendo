package dev.prateekthakur.spendo.domain.repository

import dev.prateekthakur.spendo.domain.models.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun get() : Flow<List<Expense>>
    suspend fun add(expense: Expense)
    suspend fun delete(expense: Expense)
    suspend fun deleteAll()
}