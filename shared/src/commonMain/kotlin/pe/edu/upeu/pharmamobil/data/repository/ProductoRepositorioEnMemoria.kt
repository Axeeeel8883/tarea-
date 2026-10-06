package pe.edu.upeu.pharmamobil.data.repository

import pe.edu.upeu.pharmamobil.domain.model.Producto
import pe.edu.upeu.pharmamobil.domain.repository.ProductoRepository

class ProductoRepositorioEnMemoria : ProductoRepository {
    private val productos = listOf(
        Producto(1, "Paracetamol 500 mg", 4.50, 24),
        Producto(2, "Ibuprofeno 400 mg", 8.90, 17),
        Producto(3, "Vitamina C 1 g", 12.50, 31),
        Producto(4, "Alcohol medicinal 500 ml", 9.80, 10)
    )

    override fun listar(): List<Producto> = productos

    override fun buscarPorId(id: Long): Producto? =
        productos.firstOrNull { it.id == id }
}
