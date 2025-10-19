package dev.prateekthakur.spendo.presentation.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.util.Locale

@Composable
fun DisplayAmount(
    amount: Double,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    locale: Locale = Locale.getDefault()
) {

    val formattedAmount = NumberFormat.getCurrencyInstance(locale).apply {
        maximumFractionDigits = 2
    }.format(amount)

    Row(modifier = modifier, verticalAlignment = Alignment.Bottom) {
        Text("₹", style = textStyle.copy(fontSize = textStyle.fontSize.times(0.8), color = Color.Gray))
        Spacer(modifier = modifier.width(1.dp))
        Text(formattedAmount.removeRange(0..0), style = textStyle)
    }
}

@Preview(showBackground = true)
@Composable
private fun DisplayAmountPreview() {
    DisplayAmount(200.0)
}