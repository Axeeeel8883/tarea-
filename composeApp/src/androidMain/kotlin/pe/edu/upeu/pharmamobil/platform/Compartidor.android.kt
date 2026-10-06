package pe.edu.upeu.pharmamobil.platform

import android.content.Context
import android.content.Intent

actual class Compartidor(private val context: Context) {
    actual fun compartirProducto(nombre: String, precio: Double) {
        val texto = "$nombre - ${formatearSoles(precio)}"
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, texto)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(Intent.createChooser(intent, "Compartir producto").apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }
}
