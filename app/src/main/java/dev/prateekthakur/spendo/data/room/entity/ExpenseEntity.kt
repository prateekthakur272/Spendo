package dev.prateekthakur.spendo.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import dev.prateekthakur.spendo.data.room.db.Converters
import dev.prateekthakur.spendo.domain.models.Expense
import dev.prateekthakur.spendo.domain.models.ExpenseType
import java.time.Instant

@Entity(tableName = "expenses")
@TypeConverters(Converters::class)
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val amount: Double,
    val type: ExpenseType,
    val timestamp: Instant
)


fun Expense.toEntity() = ExpenseEntity(
    id = id ?: 0,
    amount = amount,
    type = type,
    timestamp = timestamp
)

fun ExpenseEntity.toDomain() = Expense(
    id = id,
    amount = amount,
    type = type,
    timestamp = timestamp
)