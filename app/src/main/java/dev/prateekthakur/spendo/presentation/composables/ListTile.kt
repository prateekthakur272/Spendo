package dev.prateekthakur.spendo.presentation.composables

import android.text.format.DateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.prateekthakur.spendo.domain.models.CategoryExpense
import dev.prateekthakur.spendo.domain.models.Expense
import dev.prateekthakur.spendo.utils.getColor
import dev.prateekthakur.spendo.utils.getIcon
import java.util.Date


@Composable
fun ExpenseListItem(
    expense: Expense,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {}
) {

    Row(
        modifier = modifier.combinedClickable(onClick = onClick, onLongClick = onLongClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(getColor(expense.type)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painterResource(getIcon(expense.type)),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.surface
            )
        }
        Spacer(modifier = modifier.width(12.dp))
        Column {
            DisplayAmount(
                expense.amount,
                textStyle = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                expense.type.name, color = Color.Gray, style = MaterialTheme.typography.labelSmall
            )
        }
        Spacer(modifier = modifier.weight(1f))
        Column {
            Text(
                DateFormat.format("dd MMM yyyy", Date.from(expense.timestamp)).toString(),
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun CategoryExpenseListItem(
    categoryExpense: CategoryExpense,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(modifier = modifier.clickable {
        onClick()
    }, verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(getColor(categoryExpense.type)), contentAlignment = Alignment.Center
        ) {
            Icon(
                painterResource(getIcon(categoryExpense.type)),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.surface
            )
        }
        Spacer(modifier = modifier.width(12.dp))
        Text(
            categoryExpense.type.name,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.weight(1f))
        DisplayAmount(
            categoryExpense.total,
            textStyle = MaterialTheme.typography.titleMedium
        )
    }
}