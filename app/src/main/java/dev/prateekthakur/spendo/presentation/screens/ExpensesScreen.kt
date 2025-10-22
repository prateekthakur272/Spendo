package dev.prateekthakur.spendo.presentation.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.prateekthakur.spendo.domain.models.ExpenseType
import dev.prateekthakur.spendo.domain.models.PeriodFilter
import dev.prateekthakur.spendo.presentation.composables.DisplayAmount
import dev.prateekthakur.spendo.presentation.composables.ExpenseListItem
import dev.prateekthakur.spendo.presentation.viewmodels.ExpenseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen(
    expenseViewModel: ExpenseViewModel,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    typeFilter: ExpenseType? = null,
    periodFilter: PeriodFilter? = null
) {

    val expenses by expenseViewModel.state.collectAsStateWithLifecycle()

    var selectedPeriod by rememberSaveable {
        mutableStateOf(
            periodFilter ?: PeriodFilter.LAST_MONTH
        )
    }
    var selectedType by rememberSaveable { mutableStateOf(typeFilter) }

    LaunchedEffect(selectedPeriod, selectedType) {
        expenseViewModel.getExpenses(selectedPeriod, selectedType)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface, topBar = {
            TopAppBar(title = { Text("Expenses") }, navigationIcon = {
                IconButton(onClick = {
                    navHostController.safePopBackStack()
                }) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
                }
            })
        }) { safePadding ->
        LazyColumn(
            modifier = modifier
                .padding(safePadding)
                .padding(horizontal = 16.dp)
        ) {

            item {
                Text("Period")
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    PeriodFilter.entries.toList().forEach {
                        val selected = it == selectedPeriod
                        FilterChip(selected = selected, onClick = {
                            selectedPeriod = it
                        }, label = {
                            Text(it.name, style = MaterialTheme.typography.labelSmall)
                        })
                    }
                }
                Text("Type")
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip(
                        selected = selectedType == null,
                        onClick = { selectedType = null },
                        label = {
                            Text("All")
                        })
                    ExpenseType.entries.toList().forEach {
                        val selected = it == selectedType
                        FilterChip(selected = selected, onClick = {
                            selectedType = it
                        }, label = {
                            Text(it.name, style = MaterialTheme.typography.labelSmall)
                        })
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Total",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    DisplayAmount(expenses.map { it.amount }.reduceOrNull { acc, d -> acc + d }
                        ?: 0.0,
                        textStyle = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.tertiary))
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(expenses) {
                ExpenseListItem(it)
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        "That's it for now",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }
}