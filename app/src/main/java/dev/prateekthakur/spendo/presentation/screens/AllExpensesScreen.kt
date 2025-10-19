package dev.prateekthakur.spendo.presentation.screens

import android.text.format.DateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.prateekthakur.spendo.domain.models.Expense
import dev.prateekthakur.spendo.presentation.composables.DisplayAmount
import dev.prateekthakur.spendo.presentation.viewmodels.ExpenseViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllExpensesScreen(expenseViewModel: ExpenseViewModel, modifier: Modifier = Modifier) {

    val expenses by expenseViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("All Expenses")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) { safePadding ->
        LazyColumn(modifier = modifier.padding(safePadding)) {
            items(expenses) {
                ExpenseListItem(it)
            }
        }
    }
}

@Composable
fun ExpenseListItem(expense: Expense, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.Gray.copy(alpha = 0.4f))
        )
        Spacer(modifier = modifier.width(12.dp))
        Column {
            DisplayAmount(
                expense.amount,
                textStyle = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                expense.type.name,
                color = Color.Gray,
                style = MaterialTheme.typography.labelMedium
            )
        }
        Spacer(modifier = modifier.weight(1f))
        Column {
            Text(
                DateFormat.format("dd/MM/yyyy", Date.from(expense.timestamp)).toString(),
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpenseListItemPreview() {
    ExpenseListItem(Expense(200.0))
}