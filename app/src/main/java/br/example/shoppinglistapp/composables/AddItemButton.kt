package br.example.shoppinglistapp.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ColumnScope.AddItemButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        Text(text = "Add Item")
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    Column {
        AddItemButton(
            onClick = { }
        )
    }
}

