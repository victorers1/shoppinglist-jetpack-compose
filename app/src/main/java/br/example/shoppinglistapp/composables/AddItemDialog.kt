package br.example.shoppinglistapp.composables

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.example.shoppinglistapp.ShoppingItem
import br.example.shoppinglistapp.ui.theme.ShoppingListAppTheme

@Composable
fun AddItemDialog(
    onItemAdded: (ShoppingItem) -> Unit,
    onDismissRequest: () -> Unit,
    nextItemId: Int
) {
    val mContext = LocalContext.current
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    fun createShoppingItem(name: String, quantity: Int): ShoppingItem {
        return ShoppingItem(
            id = nextItemId,
            name = name,
            quantity = quantity,
        )
    }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = "Add Shopping Item") },
        confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    if (itemName.isNotBlank()) {
                        onItemAdded(
                            createShoppingItem(itemName, itemQuantity.toInt()),
                        )
                    } else {
                        Toast.makeText(
                            mContext,
                            "Insert a name for the item",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }) {
                    Text(text = "Add")
                }
                Button(onClick = onDismissRequest) {
                    Text(text = "Cancel")
                }
            }
        },
        text = {
            Column {
                DialogTextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    placeholder = "Enter a name",
                    label = "Name"
                )
                DialogTextField(
                    value = itemQuantity,
                    onValueChange = { itemQuantity = it },
                    placeholder = "Enter a quantity",
                    label = "Quantity"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AddItemDialogPreview() {
    ShoppingListAppTheme {
        AddItemDialog(
            nextItemId = 0,
            onItemAdded = { },
            onDismissRequest = { }
        )
    }
}