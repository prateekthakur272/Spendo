package dev.prateekthakur.spendo.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.himanshoe.charty.bar.BarChart
import com.himanshoe.charty.bar.model.BarData
import com.himanshoe.charty.common.ChartColor
import com.himanshoe.charty.common.LabelConfig
import dev.prateekthakur.spendo.R
import dev.prateekthakur.spendo.domain.models.CategoryExpense
import dev.prateekthakur.spendo.domain.models.Expense
import dev.prateekthakur.spendo.domain.models.ExpenseType
import dev.prateekthakur.spendo.presentation.composables.CategoryExpenseListItem
import dev.prateekthakur.spendo.presentation.composables.DisplayAmount
import dev.prateekthakur.spendo.presentation.composables.ExpenseListItem
import dev.prateekthakur.spendo.presentation.viewmodels.ExpenseIntent
import dev.prateekthakur.spendo.presentation.viewmodels.ExpenseViewModel
import dev.prateekthakur.spendo.utils.getColor

@Composable
fun HomeScreen(
    expenseViewModel: ExpenseViewModel,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {

    val expenses by expenseViewModel.state.collectAsStateWithLifecycle()
    val categoryExpenses = expenses.groupBy { it.type }.map { (k, v) ->
        CategoryExpense(type = k, total = v.map { it.amount }.reduceOrNull { a, b -> a + b } ?: 0.0)
    }

    LaunchedEffect(Unit) {
        expenseViewModel(ExpenseIntent.Get())
    }

    Scaffold(containerColor = MaterialTheme.colorScheme.surface, topBar = {
        AppBar(onSettingsClick = {
            navHostController.navigate("/settings")
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            navHostController.navigate("/create")
        }) {
            Icon(
                Icons.Default.Add,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.surface
            )
        }
    }) { safePadding ->
        LazyColumn(
            modifier = modifier
                .padding(safePadding)
                .padding(horizontal = 16.dp)
        ) {
            item {
                OverviewCard(categoryExpenses)
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                ExpenseCategories(categoryExpenses, onAction = {
                    navHostController.navigate("/expenses")
                }, onClickItem = {
                    navHostController.navigate("/expenses?type=$it")
                })
            }
            item {
                RecentExpenses(expenses.take(5), onAction = {
                    navHostController.navigate("/expenses")
                })
            }
        }
    }
}

@Composable
fun OverviewCard(categoryExpenses: List<CategoryExpense>, modifier: Modifier = Modifier) {
    Box(modifier = modifier.padding(16.dp)) {
        val chartData = categoryExpenses.map {
            BarData(
                xValue = it.type, yValue = it.total.toFloat(), barColor = ChartColor.Solid(
                    getColor(it.type)
                )
            )
        }
        val total = categoryExpenses.map { it.total }.reduceOrNull { acc, d -> acc + d } ?: 0.0

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Total expense this month", style = MaterialTheme.typography.labelMedium)
            DisplayAmount(total)
            Spacer(modifier = modifier.height(16.dp))
            BarChart(
                { chartData },
                modifier = Modifier.height(100.dp),
                labelConfig = LabelConfig.default().copy(
                    showYLabel = true,
                    showXLabel = false,
                    labelTextStyle = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurface)
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(onSettingsClick: () -> Unit, modifier: Modifier = Modifier) {
    TopAppBar(title = {
        Text(stringResource(R.string.app_name))
    }, actions = {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    onSettingsClick()
                }
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center) {
            Icon(Icons.Outlined.Settings, contentDescription = null)
        }
        Spacer(modifier = Modifier.width(8.dp))
    })
}

@Composable
fun RecentExpenses(expenses: List<Expense>, onAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Recent expenses")
            TextButton(onClick = onAction) {
                Text("View all")
            }
        }

        expenses.map {
            ExpenseListItem(it)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ExpenseCategories(
    categoryExpenses: List<CategoryExpense>,
    onAction: () -> Unit,
    onClickItem: (ExpenseType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Expense per category")
            TextButton(onClick = onAction) {
                Text("View all")
            }
        }

        categoryExpenses.map {
            CategoryExpenseListItem(it, onClick = { onClickItem(it.type) })
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}