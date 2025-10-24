package dev.prateekthakur.spendo.presentation.composables

import android.text.format.DateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.prateekthakur.spendo.domain.models.Expense
import dev.prateekthakur.spendo.utils.getColor
import dev.prateekthakur.spendo.utils.getIcon
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseMenu(
    expense: Expense,
    onDelete: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(onDismissRequest = onCancel) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
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
            DisplayAmount(expense.amount, textStyle = MaterialTheme.typography.titleLarge)
            Text(expense.type.name, color = Color.Gray, style = MaterialTheme.typography.labelSmall)
            Text(
                DateFormat.format("dd MMM yyyy", Date.from(expense.timestamp)).toString(),
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )

            TextButton(onClick = {
                onDelete()
                onCancel()
            }) {
                Text("Delete")
            }
        }
    }
}