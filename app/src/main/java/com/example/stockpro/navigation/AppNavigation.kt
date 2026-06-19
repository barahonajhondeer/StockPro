package com.example.stockpro.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.stockpro.screens.*
import com.example.stockpro.viewmodel.StockViewModel

@Composable
fun AppNavigation() {

    val navController =
        rememberNavController()

    val stockViewModel: StockViewModel =
        viewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {

            LoginScreen(navController)
        }

        composable(
            "inventario/{operario}"
        ) {

            val operario =
                it.arguments?.getString("operario")
                    ?: ""

            InventarioScreen(
                navController,
                operario,
                stockViewModel
            )
        }

        composable(
            "detalle/{productoId}"
        ) {

            val id =
                it.arguments
                    ?.getString("productoId")
                    ?.toIntOrNull()
                    ?: 0

            DetalleProductoScreen(
                navController,
                id,
                stockViewModel
            )
        }

        composable("reporte") {

            ReporteScreen(
                navController,
                stockViewModel
            )
        }
    }
}