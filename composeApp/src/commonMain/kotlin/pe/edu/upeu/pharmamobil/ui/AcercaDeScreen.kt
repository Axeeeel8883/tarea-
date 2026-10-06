package pe.edu.upeu.pharmamobil.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pe.edu.upeu.pharmamobil.platform.InfoDispositivo

@Composable
fun AcercaDeScreen() {
    val dispositivo = InfoDispositivo()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Acerca de PharmaMobil", style = MaterialTheme.typography.headlineSmall)
        Text("Sistema operativo: ${dispositivo.sistema}")
        Text("Versión: ${dispositivo.version}")
    }
}
