package pe.edu.upeu.pharmamobil.presentation.detalle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pe.edu.upeu.pharmamobil.presentation.producto.ProductoUi

@Composable
fun DetalleProductoScreen(
    producto: ProductoUi,
    onVolver: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Detalle del producto", style = MaterialTheme.typography.headlineSmall)
        Text(producto.nombre, style = MaterialTheme.typography.titleLarge)
        Text("Precio: ${producto.precio}")
        Text("Stock: ${producto.stock}")
        Button(onClick = onVolver) {
            Text("Volver")
        }
    }
}
