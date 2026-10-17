package com.yoshua.vestiapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TarjetasResumen(
    libres: Int,
    ocupados: Int,
    enEspera: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ItemMetrica(
            titulo = "LIBRES",
            valor = libres.toString(),
            colorFondo = Color(0xFFE8F5E9),
            colorTexto = Color(0xFF2E7D32),
            modifier = Modifier.weight(1f)
        )
        ItemMetrica(
            titulo = "OCUPADOS",
            valor = ocupados.toString(),
            colorFondo = Color(0xFFFFF3E0),
            colorTexto = Color(0xFFE65100),
            modifier = Modifier.weight(1f)
        )
        ItemMetrica(
            titulo = "EN ESPERA",
            valor = enEspera.toString(),
            colorFondo = Color(0xFFEDE7F6),
            colorTexto = Color(0xFF512DA8),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun ItemMetrica(
    titulo: String,
    valor: String,
    colorFondo: Color,
    colorTexto: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(colorFondo, shape = RoundedCornerShape(12.dp))
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text = titulo,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = colorTexto
        )
        Text(
            text = valor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorTexto
        )
    }
}