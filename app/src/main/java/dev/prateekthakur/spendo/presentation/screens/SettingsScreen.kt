package dev.prateekthakur.spendo.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import dev.prateekthakur.spendo.presentation.viewmodels.ExpenseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    expenseViewModel: ExpenseViewModel,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {

    var showClearDialog by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface, topBar = {
            TopAppBar(title = {
                Text("Settings")
            }, navigationIcon = {
                IconButton(onClick = {
                    navHostController.popBackStack()
                }) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
                }
            })
        }) { safePadding ->
        LazyColumn(modifier = modifier.padding(safePadding)) {
            item {
                ListItem(headlineContent = {
                    Text("Auto add expenses")
                }, trailingContent = {
                    Switch(checked = true, onCheckedChange = {})
                }, modifier = Modifier.clickable {

                })
            }
            item {
                ListItem(headlineContent = {
                    Text("Clear all expenses", color = MaterialTheme.colorScheme.error)
                }, modifier = Modifier.clickable {
                    showClearDialog = true
                })
            }
            item {
                AppInfoFooter()
            }
        }
    }

    if (showClearDialog) {
        ClearExpensesDialog(onDismissRequest = {
            showClearDialog = false
        }, onConfirm = {
            expenseViewModel.clearExpenses()
            showClearDialog = false
        })
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClearExpensesDialog(
    onDismissRequest: () -> Unit, onConfirm: () -> Unit, modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(shape = MaterialTheme.shapes.small) {
            Column(
                modifier = modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Clear all expenses?")
                Spacer(modifier = modifier.height(4.dp))
                Text(
                    "Do you want to clear all expenses?",
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = modifier.height(8.dp))
                Row {
                    Button(onClick = onConfirm) {
                        Text("Yes, clear")
                    }
                    Spacer(modifier = modifier.width(8.dp))
                    TextButton(onClick = onDismissRequest) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}


@Composable
fun AppInfoFooter(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
    val version = packageInfo.versionName ?: "Unknown"
    val name = packageInfo.applicationInfo?.loadLabel(context.packageManager).toString()

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "$name $version",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}