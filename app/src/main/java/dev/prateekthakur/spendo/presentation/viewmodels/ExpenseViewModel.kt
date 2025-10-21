package dev.prateekthakur.spendo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.prateekthakur.spendo.domain.models.Expense
import dev.prateekthakur.spendo.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class ExpenseViewModel(private val expenseRepository: ExpenseRepository) : ViewModel() {

    private val _state = MutableStateFlow<List<Expense>>(emptyList())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            expenseRepository.get().collect {
                Timber.d(it.toString())
                _state.emit(it)
            }
        }
    }

    fun createExpense(expense: Expense) {
        viewModelScope.launch {
            expenseRepository.add(expense)
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            expenseRepository.delete(expense)
        }
    }
}