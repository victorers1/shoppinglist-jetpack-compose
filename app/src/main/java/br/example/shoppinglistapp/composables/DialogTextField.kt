package br.example.shoppinglistapp.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.example.shoppinglistapp.ui.theme.ShoppingListAppTheme

@Composable
fun DialogTextField(
    value: String,
    label: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        singleLine = true,
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun TextInputPreview() {
    ShoppingListAppTheme {
        DialogTextField(
             value = "Name",
            label = "Label",
            placeholder = "Placeholder",
            onValueChange = { }
        )
    }
}