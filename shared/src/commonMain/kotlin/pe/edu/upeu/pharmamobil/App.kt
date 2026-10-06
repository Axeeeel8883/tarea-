package pe.edu.upeu.pharmamobil

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import pe.edu.upeu.pharmamobil.presentation.detalle.DetalleProductoScreen
import pe.edu.upeu.pharmamobil.presentation.detalle.DetalleProductoViewModel
import pe.edu.upeu.pharmamobil.presentation.producto.ProductoScreen
import pe.edu.upeu.pharmamobil.presentation.producto.ProductoViewModel
import pe.edu.upeu.pharmamobil.presentation.producto.toUi

@Composable
fun App() {
    MaterialTheme {
        val productoViewModel = koinViewModel<ProductoViewModel>()
        val detalleViewModel = koinViewModel<DetalleProductoViewModel>()
        var productoSeleccionado by remember { mutableStateOf<Long?>(null) }

        Surface(modifier = Modifier.fillMaxSize()) {
            val id = productoSeleccionado
            if (id == null) {
                ProductoScreen(
                    productos = productoViewModel.productos,
                    onProductoClick = { productoSeleccionado = it }
                )
            } else {
                val producto = detalleViewModel.obtener(id)
                if (producto == null) {
                    productoSeleccionado = null
                } else {
                    DetalleProductoScreen(
                        producto = producto.toUi(),
                        onVolver = { productoSeleccionado = null }
                    )
                }
            }
        }
    }
}
