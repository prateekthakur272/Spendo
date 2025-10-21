package dev.prateekthakur.spendo.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.prateekthakur.spendo.domain.models.ExpenseType
import dev.prateekthakur.spendo.presentation.composables.ExpenseListItem
import dev.prateekthakur.spendo.presentation.viewmodels.ExpenseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen(
    expenseViewModel: ExpenseViewModel, modifier: Modifier = Modifier, type: ExpenseType? = null
) {
    val expenses by expenseViewModel.state.collectAsStateWithLifecycle()
    val filteredExpenses =
        type?.let { expenses.filter { expense -> expense.type == it } } ?: expenses

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = { Text(type?.name ?: "All") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { safePadding ->
        LazyColumn(
            modifier = modifier
                .padding(safePadding)
                .padding(horizontal = 16.dp)
        ) {
            items(filteredExpenses) {
                ExpenseListItem(it)
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text("That's it for now", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.tertiary)
                }
            }
        }
    }
}