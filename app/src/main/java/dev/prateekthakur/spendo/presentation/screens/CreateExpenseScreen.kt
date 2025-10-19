package dev.prateekthakur.spendo.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.prateekthakur.spendo.R
import dev.prateekthakur.spendo.presentation.composables.InvisibleTextField

@Composable
fun CreateExpenseScreen(modifier: Modifier = Modifier) {
    var amount by rememberSaveable { mutableStateOf("") }
    var comment by rememberSaveable { mutableStateOf("") }

    Scaffold { safePadding ->
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
                amount+=it
            }, onDelete = {
                amount = amount.dropLast(1)
            }, onSubmit = {

            })
        }
    }
}


@Preview
@Composable
private fun CreateExpenseScreenPreview() {
    CreateExpenseScreen()
}

@Composable
fun NumberPad(
    modifier: Modifier = Modifier,
    onType: (String) -> Unit,
    onDelete: () -> Unit,
    onSubmit: () -> Unit
) {

    val numbers = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
    )

    Row(modifier = modifier.aspectRatio(1f)) {
        Box(modifier = modifier.weight(3f)) {
            Column {
                numbers.map { row ->
                    Row {
                        row.map { column ->
                            NumberPadButton(
                                onClick = {
                                    onType(column)
                                }, modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .padding(2.dp)
                            ) {
                                Text(column, style = MaterialTheme.typography.titleMedium)
                            }
                        }
                    }
                }
                Row {
                    NumberPadButton(
                        onClick = {
                            onType("0")
                        }, modifier
                            .weight(2f)
                            .aspectRatio(2f)
                            .padding(2.dp)
                    ) {
                        Text("0", style = MaterialTheme.typography.titleMedium)
                    }
                    NumberPadButton(
                        onClick = {
                            onType(".")
                        }, modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(2.dp)
                    ) {
                        Text(".", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
        Box(modifier = modifier.weight(1f)) {
            Column {
                NumberPadButton(
                    onClick = {
                        onDelete()
                    }, modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .padding(2.dp),
                    containerColor = Color.Red.copy(alpha = 0.1f)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.rounded_backspace_24),
                        contentDescription = null
                    )
                }
                NumberPadButton(
                    onClick = {}, modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .padding(2.dp),
                    containerColor = Color.Blue.copy(alpha = 0.1f)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.rounded_calendar_month_24),
                        contentDescription = null
                    )
                }
                NumberPadButton(
                    onClick = {
                        onSubmit()
                    }, modifier
                        .weight(2f)
                        .aspectRatio(1 / 2f)
                        .padding(2.dp),
                    containerColor = Color.Black
                ) {
                    Icon(
                        painter = painterResource(R.drawable.rounded_check_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NumberPadPreview() {
    NumberPad(onType = {}, onDelete = {}, onSubmit = {})
}

@Composable
private fun NumberPadButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Gray.copy(alpha = 0.1f),
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(containerColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}
