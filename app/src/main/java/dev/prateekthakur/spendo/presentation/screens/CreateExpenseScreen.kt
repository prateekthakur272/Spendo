package dev.prateekthakur.spendo.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.prateekthakur.spendo.domain.models.Expense
import dev.prateekthakur.spendo.domain.models.ExpenseType
import dev.prateekthakur.spendo.presentation.composables.InvisibleTextField
import dev.prateekthakur.spendo.presentation.composables.NumberPad
import dev.prateekthakur.spendo.presentation.viewmodels.ExpenseIntent
import dev.prateekthakur.spendo.presentation.viewmodels.ExpenseViewModel
import dev.prateekthakur.spendo.utils.getColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateExpenseScreen(expenseViewModel: ExpenseViewModel, navHostController: NavHostController) {
    CreateExpenseScreenContent(
        expenseViewModel::invoke,
        onCreated = { navHostController.safePopBackStack() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateExpenseScreenContent(
    expenseAction: (ExpenseIntent) -> Unit, onCreated: () -> Unit, modifier: Modifier = Modifier
) {
    var amount by rememberSaveable { mutableStateOf("") }
    var comment by rememberSaveable { mutableStateOf("") }
    var type by remember { mutableStateOf(ExpenseType.ANONYMOUS) }
    var showSelector by remember { mutableStateOf(true) }

    Scaffold(containerColor = MaterialTheme.colorScheme.surface) { safePadding ->
        Column(
            modifier = modifier
                .padding(safePadding)
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = modifier.weight(1.5f))

            Text("Add Expense", style = MaterialTheme.typography.titleMedium)

            Text(
                amount.ifEmpty { "0" },
                style = MaterialTheme.typography.displayLarge,
            )

            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(24.dp))
                    .background(getColor(type))
                    .clickable {
                        showSelector = true
                    }
                    .padding(horizontal = 28.dp, vertical = 16.dp)) {
                Text(type.name, style = MaterialTheme.typography.labelMedium)
            }
            Spacer(modifier = modifier.height(16.dp))

            InvisibleTextField(
                value = comment,
                onValueChange = { comment = it.trim() },
                placeholder = "Add comments",
                textStyle = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = modifier.weight(1f))
            NumberPad(onType = {
                if (amount.isEmpty() && it == "0") return@NumberPad
                if (amount.isEmpty() && it == ".") return@NumberPad
                if (amount.contains(".") && it == ".") return@NumberPad
                amount += it
            }, onDelete = {
                amount = amount.dropLast(1)
            }, onSubmit = {
                amount.toDoubleOrNull()?.let {
                    expenseAction(ExpenseIntent.Create(Expense(amount = it, type = type)))
                    onCreated()
                }
            })
        }
    }

    if (showSelector) {
        ModalBottomSheet(
            onDismissRequest = { showSelector = false },
        ) {
            FlowRow(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ExpenseType.entries.toList().map {
                    ExpenseTypeChip(it, onClick = {
                        type = it
                        showSelector = false
                    })
                }
            }
        }
    }
}

@Composable
fun ExpenseTypeChip(type: ExpenseType, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .clip(RoundedCornerShape(24.dp))
        .background(getColor(type))
        .clickable {
            onClick()
        }
        .padding(horizontal = 28.dp, vertical = 16.dp)) {
        Text(type.name, style = MaterialTheme.typography.labelMedium)
    }
}