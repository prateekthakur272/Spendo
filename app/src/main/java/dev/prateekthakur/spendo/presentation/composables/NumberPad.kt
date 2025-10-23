package dev.prateekthakur.spendo.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.prateekthakur.spendo.R


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
                                Text(column, style = MaterialTheme.typography.headlineMedium)
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
                        Text("0", style = MaterialTheme.typography.headlineMedium)
                    }
                    NumberPadButton(
                        onClick = {
                            onType(".")
                        }, modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(2.dp)
                    ) {
                        Text(".", style = MaterialTheme.typography.headlineMedium)
                    }
                }
            }
        }
        Box(modifier = modifier.weight(1f)) {
            Column {
                NumberPadButton(
                    onClick = {
                        onDelete()
                    },
                    modifier
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
                    onClick = {},
                    modifier
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
                    },
                    modifier
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
