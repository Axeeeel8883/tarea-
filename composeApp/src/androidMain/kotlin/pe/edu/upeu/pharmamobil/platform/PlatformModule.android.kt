package pe.edu.upeu.pharmamobil.platform

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule = module {
    single { Compartidor(androidContext()) }
}
