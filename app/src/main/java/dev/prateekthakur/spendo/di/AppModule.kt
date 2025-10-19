package dev.prateekthakur.spendo.di

import dev.prateekthakur.spendo.data.repository.ExpenseRepositoryRoomImpl
import dev.prateekthakur.spendo.domain.repository.ExpenseRepository
import dev.prateekthakur.spendo.presentation.viewmodels.ExpenseViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    single <ExpenseRepository>{
        ExpenseRepositoryRoomImpl(get())
    }

    viewModel <ExpenseViewModel>{
        ExpenseViewModel(get())
    }
}