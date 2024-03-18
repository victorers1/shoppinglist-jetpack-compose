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
fun EditItemDialog(
    item: ShoppingItem,
    onItemSaved: (id: Int, name: String, quantity: Int) -> Unit,
    onDismissRequest: () -> Unit,
) {
    val mContext = LocalContext.current
    var itemName by remember { mutableStateOf(item.name) }
    var itemQuantity by remember { mutableStateOf(item.quantity.toString()) }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = "Edit Shopping Item") },
        confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    val quantity = itemQuantity.toInt()
                    if (itemName.isNotBlank() && quantity > 0) {
                        onItemSaved(item.id, itemName, quantity)
                        onDismissRequest()
                    } else {
                        Toast.makeText(
                            mContext,
                            "Insert valid values for the item",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }) {
                    Text(text = "Save")
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
                    label = "Name",
                    placeholder = "Enter a name",
                )
                DialogTextField(
                    value = itemQuantity,
                    onValueChange = { itemQuantity = it },
                    label = "Quantity",
                    placeholder = "Enter a quantity",
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun EditItemDialogPreview() {
    ShoppingListAppTheme {
        AddItemDialog(
            nextItemId = 0,
            onItemAdded = { },
            onDismissRequest = { }
        )
    }
}