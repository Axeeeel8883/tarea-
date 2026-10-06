package pe.edu.upeu.pharmamobil.presentation.producto

import pe.edu.upeu.pharmamobil.domain.model.Producto

data class ProductoUi(
    val id: Long,
    val nombre: String,
    val precio: String,
    val stock: Int
)

fun Producto.toUi() = ProductoUi(
    id = id,
    nombre = nombre,
    precio = precio.toString(),
    stock = stock
)
