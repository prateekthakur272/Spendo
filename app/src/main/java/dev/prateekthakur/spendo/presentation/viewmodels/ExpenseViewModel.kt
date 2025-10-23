package dev.prateekthakur.spendo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.prateekthakur.spendo.domain.models.Expense
import dev.prateekthakur.spendo.domain.models.ExpenseType
import dev.prateekthakur.spendo.domain.models.PeriodFilter
import dev.prateekthakur.spendo.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class ExpenseViewModel(private val expenseRepository: ExpenseRepository) : ViewModel() {

    private val _state = MutableStateFlow<List<Expense>>(emptyList())
    val state = _state.asStateFlow()

    operator fun invoke(intent: ExpenseIntent) {
        Timber.d(intent.toString())
        when(intent){
            is ExpenseIntent.Create -> createExpense(intent.expense)
            is ExpenseIntent.Delete -> deleteExpense(intent.expense)
            is ExpenseIntent.Get -> getExpenses(intent.periodFilter, intent.typeFilter)
            ExpenseIntent.Clear -> clearExpenses()
        }
    }

    private fun getExpenses(periodFilter: PeriodFilter? = null, typeFilter: ExpenseType? = null){
        viewModelScope.launch {
            expenseRepository.get(periodFilter, typeFilter).collect {
                _state.emit(it)
            }
        }
    }

    private fun createExpense(expense: Expense) {
        viewModelScope.launch {
            expenseRepository.add(expense)
        }
    }

    private fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            expenseRepository.delete(expense)
        }
    }

    private fun clearExpenses(){
        viewModelScope.launch {
            expenseRepository.deleteAll()
        }
    }
}