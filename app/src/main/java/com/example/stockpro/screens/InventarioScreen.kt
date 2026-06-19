package com.example.stockpro.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockpro.viewmodel.StockViewModel

@Composable
fun InventarioScreen(
    navController: NavController,
    operario: String,
    viewModel: StockViewModel
) {

    var soloCriticos by remember {
        mutableStateOf(false)
    }

    val productos = if (soloCriticos)
        viewModel.obtenerProductosEnRiesgo()
    else
        viewModel.productos

    Scaffold(

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("reporte")
                }
            ) {
                Text("R")
            }
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            Text("Operario: $operario")

            Spacer(modifier = Modifier.height(10.dp))

            Row {

                Button(
                    onClick = {
                        soloCriticos = false
                    }
                ) {
                    Text("Ver Todo")
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    onClick = {
                        soloCriticos = true
                    }
                ) {
                    Text("Stock Crítico")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn {

                items(productos) { producto ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {

                                navController.navigate(
                                    "detalle/${producto.id}"
                                )
                            }
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(producto.nombre)

                            Text(
                                "Precio: $${producto.precio}"
                            )

                            Text(
                                "Stock: ${producto.stockActual}",
                                color =
                                    if (producto.stockActual < 5)
                                        Color.Red
                                    else
                                        Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}