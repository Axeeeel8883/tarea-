package pe.edu.upeu.pharmamobil.presentation.producto

import androidx.lifecycle.ViewModel
import pe.edu.upeu.pharmamobil.domain.repository.ProductoRepository

class ProductoViewModel(
    repository: ProductoRepository
) : ViewModel() {
    val productos: List<ProductoUi> = repository.listar().map { it.toUi() }
}
