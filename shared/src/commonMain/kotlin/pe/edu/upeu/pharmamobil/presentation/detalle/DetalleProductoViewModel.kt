package pe.edu.upeu.pharmamobil.presentation.detalle

import androidx.lifecycle.ViewModel
import pe.edu.upeu.pharmamobil.domain.model.Producto
import pe.edu.upeu.pharmamobil.domain.repository.ProductoRepository

class DetalleProductoViewModel(
    private val repository: ProductoRepository
) : ViewModel() {
    fun obtener(id: Long): Producto? = repository.buscarPorId(id)
}
