package com.yoshua.vestiapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yoshua.vestiapp.ui.screens.DashboardScreen
import com.yoshua.vestiapp.ui.screens.FormularioClienteScreen
import com.yoshua.vestiapp.viewmodel.ProbadorViewModel

sealed class Pantalla(val ruta: String) {
    object Dashboard : Pantalla("dashboard")
    object Formulario : Pantalla("formulario?probadorId={probadorId}") {
        fun crearRuta(probadorId: Int? = null) = "formulario?probadorId=${probadorId ?: -1}"
    }
}

@Composable
fun AppNavigation(viewModel: ProbadorViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Pantalla.Dashboard.ruta
    ) {
        composable(Pantalla.Dashboard.ruta) {
            DashboardScreen(
                viewModel = viewModel,
                onAbrirFormulario = {
                    navController.navigate(Pantalla.Formulario.crearRuta())
                }
            )
        }

        composable(
            route = Pantalla.Formulario.ruta,
            arguments = listOf(
                navArgument("probadorId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val probadorId = backStackEntry.arguments?.getInt("probadorId").takeIf { it != -1 }
            FormularioClienteScreen(
                viewModel = viewModel,
                probadorId = probadorId,
                onVolver = { navController.popBackStack() }
            )
        }
    }
}