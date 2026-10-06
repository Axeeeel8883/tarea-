package pe.edu.upeu.pharmamobil.platform

import org.koin.dsl.module

actual val platformModule = module {
    single { Compartidor() }
}
