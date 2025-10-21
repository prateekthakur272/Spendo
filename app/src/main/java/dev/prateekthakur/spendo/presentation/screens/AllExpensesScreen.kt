package dev.prateekthakur.spendo.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.prateekthakur.spendo.presentation.composables.ExpenseListItem
import dev.prateekthakur.spendo.presentation.viewmodels.ExpenseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllExpensesScreen(expenseViewModel: ExpenseViewModel, modifier: Modifier = Modifier) {

    val expenses by expenseViewModel.state.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text("All Expenses")
            })
    }, floatingActionButton = {
        FloatingActionButton(onClick = {}) {
            Icon(Icons.Rounded.Add, contentDescription = null)
        }
    }) { safePadding ->
        LazyColumn(modifier = modifier.padding(safePadding)) {
            items(expenses) {
                ExpenseListItem(it)
            }
        }
    }
}
