package br.example.shoppinglistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.example.shoppinglistapp.composables.AddItemButton
import br.example.shoppinglistapp.composables.AddItemDialog
import br.example.shoppinglistapp.composables.EditItemDialog
import br.example.shoppinglistapp.composables.ShoppingListItem
import br.example.shoppinglistapp.ui.theme.ShoppingListAppTheme

@Composable
fun ShoppingListApp() {
    var shoppingItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var newItemDialogIsOpen by remember { mutableStateOf(false) }
    var editingItemId by remember { mutableStateOf<Int?>(null) }

    val editItem: (Int, String, Int) -> Unit = { id: Int, name: String, quantity: Int ->
        if (editingItemId != null) {
            shoppingItems = shoppingItems.map {
                if (id == it.id) it.copy(name = name, quantity = quantity) else it
            }
            editingItemId = null
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        AddItemButton(
            onClick = { newItemDialogIsOpen = true }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(shoppingItems) { shoppingItem ->
                ShoppingListItem(
                    item = shoppingItem,
                    onEditClick = { editingItemId = shoppingItems.indexOf(shoppingItem) },
                    onDeleteCLick = { shoppingItems -= shoppingItem },
                )
            }
        }
    }

    if (newItemDialogIsOpen) {
        AddItemDialog(
            onItemAdded = {
                shoppingItems += it
                newItemDialogIsOpen = false
            },
            onDismissRequest = { newItemDialogIsOpen = false },
            nextItemId = shoppingItems.size + 1
        )
    } else if (editingItemId != null) {
        EditItemDialog(
            item = shoppingItems[editingItemId ?: 0],
            onItemSaved = editItem,
            onDismissRequest = { editingItemId = null },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    ShoppingListAppTheme {
        ShoppingListApp()
    }
}
