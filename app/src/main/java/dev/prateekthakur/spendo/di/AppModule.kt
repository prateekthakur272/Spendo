package dev.prateekthakur.spendo.di

import dev.prateekthakur.spendo.data.repository.ExpenseRepositoryRoomImpl
import dev.prateekthakur.spendo.domain.repository.ExpenseRepository
import org.koin.dsl.module

val AppModule = module {
    single <ExpenseRepository>{
        ExpenseRepositoryRoomImpl(get())
    }
}