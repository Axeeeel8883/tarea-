package pe.edu.upeu.pharmamobil.platform

import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication

actual class Compartidor {
    actual fun compartirProducto(nombre: String, precio: Double) {
        val texto = "$nombre - ${formatearSoles(precio)}"
        val controller = UIActivityViewController(
            activityItems = listOf(texto),
            applicationActivities = null
        )
        val root = UIApplication.sharedApplication.keyWindow?.rootViewController
        root?.presentViewController(controller, animated = true, completion = null)
    }
}
