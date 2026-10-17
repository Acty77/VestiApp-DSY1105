package com.yoshua.vestiapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yoshua.vestiapp.data.local.EstadoProbador
import com.yoshua.vestiapp.data.local.ProbadorEntity

@Composable
fun TarjetaProbador(
    probador: ProbadorEntity,
    onAccionClick: (ProbadorEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    // Definición de colores según el estado
    val (colorBadge, colorTextoBadge, textoEstado, textoBoton, colorBoton) = when (probador.estado) {
        EstadoProbador.LIBRE -> Quintuple(
            Color(0xFFE8F5E9), Color(0xFF2E7D32), "LIBRE", "Iniciar uso", Color(0xFFE8F5E9)
        )
        EstadoProbador.OCUPADO -> Quintuple(
            Color(0xFFFFF3E0), Color(0xFFE65100), "OCUPADO", "Revisar", Color(0xFFFFF3E0)
        )
        EstadoProbador.TIEMPO_EXCEDIDO -> Quintuple(
            Color(0xFFFFEBEE), Color(0xFFC62828), "OCUPADO", "Tiempo excedido", Color(0xFFFFEBEE)
        )
    }

    // ANIMACIÓN DE TRANSICIÓN DE COLOR (IE 2.2.2)
    val colorBadgeAnimado by animateColorAsState(
        targetValue = colorBadge,
        animationSpec = tween(durationMillis = 500),
        label = "ColorEstadoAnimation"
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.MeetingRoom,
                    contentDescription = null,
                    tint = colorTextoBadge,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Probador 0${probador.numero}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFF1E293B)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Badge de Estado
            Text(
                text = textoEstado,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = colorTextoBadge,
                modifier = Modifier
                    .background(colorBadgeAnimado, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtítulo con tiempo
            Text(
                text = if (probador.estado == EstadoProbador.LIBRE) "Disponible para usar" else "En uso · ${probador.tiempoUsoMinutos} min",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Botón de Acción
            Button(
                onClick = { onAccionClick(probador) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorBoton,
                    contentColor = colorTextoBadge
                )
            ) {
                Text(text = textoBoton, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// Data class auxiliar para empaquetar los 5 valores de estado
private data class Quintuple<A, B, C, D, E>(
    val first: A, val second: B, val third: C, val fourth: D, val fifth: E
)