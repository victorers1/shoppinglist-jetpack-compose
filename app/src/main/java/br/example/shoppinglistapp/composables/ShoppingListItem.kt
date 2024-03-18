package br.example.shoppinglistapp.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.example.shoppinglistapp.ShoppingItem
import br.example.shoppinglistapp.ui.theme.ShoppingListAppTheme

@Composable
fun ShoppingListItem(item: ShoppingItem, onEditClick: () -> Unit, onDeleteCLick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, Color(0xFF018786)),
                shape = RoundedCornerShape(20)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${item.name}, Qtd: ${item.quantity}",
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        )
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Row {
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit item")
            }
            Spacer(modifier = Modifier.padding(4.dp))
            IconButton(onClick = onDeleteCLick) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete item")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingListItemPreview() {
    val item = ShoppingItem(name = "Item long name", quantity = 1, id = 0)
    ShoppingListAppTheme {
        ShoppingListItem(
            item = item,
            onEditClick = { },
            onDeleteCLick = { }
        )
    }
}