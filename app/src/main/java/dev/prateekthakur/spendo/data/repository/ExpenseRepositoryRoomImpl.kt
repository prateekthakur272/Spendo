package dev.prateekthakur.spendo.data.repository

import dev.prateekthakur.spendo.data.room.dao.ExpenseDao
import dev.prateekthakur.spendo.data.room.entity.toDomain
import dev.prateekthakur.spendo.data.room.entity.toEntity
import dev.prateekthakur.spendo.domain.models.Expense
import dev.prateekthakur.spendo.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExpenseRepositoryRoomImpl(private val dao: ExpenseDao) : ExpenseRepository {
    override fun get(): Flow<List<Expense>> = dao.getExpenses().map { list -> list.map { it.toDomain() } }

    override suspend fun add(expense: Expense) = dao.insert(expense.toEntity())

    override suspend fun delete(expense: Expense) = dao.deleteExpenses(listOf(expense.toEntity()))

    override suspend fun deleteAll() = dao.clearAll()
}