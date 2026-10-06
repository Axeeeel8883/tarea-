package pe.edu.upeu.pharmamobil.presentation.producto

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProductoScreen(
    productos: List<ProductoUi>,
    onProductoClick: (Long) -> Unit
) {
    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(
            text = "PharmaMobil",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "Productos",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(productos, key = { it.id }) { producto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onProductoClick(producto.id) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                        Text("Precio: ${producto.precio}")
                        Text("Stock: ${producto.stock}")
                    }
                }
            }
        }
    }
}
