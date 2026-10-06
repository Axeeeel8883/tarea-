package pe.edu.upeu.pharmamobil.domain.repository

import pe.edu.upeu.pharmamobil.domain.model.Producto

interface ProductoRepository {
    fun listar(): List<Producto>
    fun buscarPorId(id: Long): Producto?
}
