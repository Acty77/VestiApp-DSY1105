package com.yoshua.vestiapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yoshua.vestiapp.utils.VibratorHelper
import com.yoshua.vestiapp.viewmodel.ProbadorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioClienteScreen(
    viewModel: ProbadorViewModel,
    probadorId: Int?,
    onVolver: () -> Unit
) {
    val context = LocalContext.current
    val vibratorHelper = remember { VibratorHelper(context) }

    // ESTADOS DEL FORMULARIO
    var nombreCliente by remember { mutableStateOf("") }
    var cantidadPrendas by remember { mutableStateOf("") }

    // ESTADOS DE VALIDACIÓN
    var errorNombre by remember { mutableStateOf<String?>(null) }
    var errorPrendas by remember { mutableStateOf<String?>(null) }

    fun validarYGuardar() {
        val prendasInt = cantidadPrendas.toIntOrNull()

        errorNombre = when {
            nombreCliente.isBlank() -> "El nombre del cliente es obligatorio"
            nombreCliente.length < 3 -> "Debe ingresar al menos 3 caracteres"
            else -> null
        }

        errorPrendas = when {
            prendasInt == null -> "Ingrese un número válido"
            prendasInt <= 0 -> "La cantidad debe ser mayor a 0"
            prendasInt > 8 -> "El máximo permitido son 8 prendas por cliente"
            else -> null
        }

        if (errorNombre == null && errorPrendas == null) {
            vibratorHelper.vibrarExito()
            viewModel.agregarClienteAFila(nombreCliente, prendasInt!!)
            onVolver()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (probadorId != null) "Asignar Probador 0$probadorId" else "Agregar a Fila de Espera",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E293B))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {
            Text(
                text = "Datos de Registro",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Nombre del Cliente
            OutlinedTextField(
                value = nombreCliente,
                onValueChange = {
                    nombreCliente = it
                    if (errorNombre != null) errorNombre = null
                },
                label = { Text("Nombre del cliente") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                isError = errorNombre != null,
                supportingText = { errorNombre?.let { Text(it, color = Color.Red) } },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Campo Cantidad de Prendas
            OutlinedTextField(
                value = cantidadPrendas,
                onValueChange = {
                    cantidadPrendas = it
                    if (errorPrendas != null) errorPrendas = null
                },
                label = { Text("Cantidad de prendas") },
                leadingIcon = { Icon(Icons.Default.Checkroom, contentDescription = null) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = errorPrendas != null,
                supportingText = { errorPrendas?.let { Text(it, color = Color.Red) } },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón Guardar
            Button(
                onClick = { validarYGuardar() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E293B))
            ) {
                Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
                Text("Confirmar e Iniciar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}