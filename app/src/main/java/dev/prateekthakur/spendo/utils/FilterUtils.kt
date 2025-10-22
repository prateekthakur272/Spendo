package dev.prateekthakur.spendo.utils

import dev.prateekthakur.spendo.domain.models.Expense
import dev.prateekthakur.spendo.domain.models.PeriodFilter
import java.time.LocalDate
import java.time.ZoneId

fun List<Expense>.filterByTime(filter: PeriodFilter): List<Expense> {
    val now = LocalDate.now()
    val zone = ZoneId.systemDefault()

    return when (filter) {
        PeriodFilter.TODAY -> filter {
            val date = it.timestamp.atZone(zone).toLocalDate()
            date == now
        }

        PeriodFilter.LAST_WEEK -> filter {
            val date = it.timestamp.atZone(zone).toLocalDate()
            !date.isBefore(now.minusDays(7))
        }

        PeriodFilter.LAST_MONTH -> filter {
            val date = it.timestamp.atZone(zone).toLocalDate()
            !date.isBefore(now.minusMonths(1))
        }

        PeriodFilter.ALL_TIME -> this
    }
}