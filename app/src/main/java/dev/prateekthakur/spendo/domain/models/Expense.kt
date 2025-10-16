package dev.prateekthakur.spendo.domain.models

import java.time.Instant

data class Expense(
    val amount: Double,
    val type: ExpenseType = ExpenseType.ANONYMOUS,
    val timestamp: Instant = Instant.now(),
    val id: Long? = null,
)
