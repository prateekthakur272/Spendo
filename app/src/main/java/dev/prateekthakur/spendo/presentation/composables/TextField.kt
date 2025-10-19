package dev.prateekthakur.spendo.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign


@Composable
fun InvisibleTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium
) {
    val focusRequester = remember { FocusRequester() }

    Box(contentAlignment = Alignment.Center) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier.focusRequester(focusRequester),
            textStyle = textStyle.copy(textAlign = TextAlign.Center),
        )
        if (value.isEmpty()){
            Text(placeholder, style = textStyle, color = Color.Gray)
        }
    }
}