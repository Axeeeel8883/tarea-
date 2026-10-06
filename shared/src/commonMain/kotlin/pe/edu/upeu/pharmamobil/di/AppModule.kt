package pe.edu.upeu.pharmamobil.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import pe.edu.upeu.pharmamobil.data.repository.ProductoRepositorioEnMemoria
import pe.edu.upeu.pharmamobil.domain.repository.ProductoRepository
import pe.edu.upeu.pharmamobil.presentation.detalle.DetalleProductoViewModel
import pe.edu.upeu.pharmamobil.presentation.producto.ProductoViewModel

val dataModule = module {
    single<ProductoRepository> { ProductoRepositorioEnMemoria() }
}

val presentationModule = module {
    viewModel { ProductoViewModel(get()) }
    viewModel { DetalleProductoViewModel(get()) }
}

expect val platformModule: Module

fun initKoin(configuracionAdicional: KoinApplication.() -> Unit = {}) {
    startKoin {
        configuracionAdicional()
        modules(dataModule, presentationModule, platformModule)
    }
}
