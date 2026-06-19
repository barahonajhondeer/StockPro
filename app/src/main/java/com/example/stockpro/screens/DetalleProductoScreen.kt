package com.example.stockpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockpro.viewmodel.StockViewModel

@Composable
fun DetalleProductoScreen(
    navController: NavController,
    productoId: Int,
    viewModel: StockViewModel
) {

    val producto =
        viewModel.obtenerProducto(productoId)

    var stock by remember {
        mutableStateOf(
            producto?.stockActual ?: 0
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        producto?.let {

            Text(
                text = it.nombre,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(it.descripcion)

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stock.toString(),
                style = MaterialTheme.typography.displayLarge
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row {

                Button(
                    onClick = {
                        stock++
                        viewModel.actualizarStock(
                            it.id,
                            stock
                        )
                    }
                ) {
                    Text("+1")
                }

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Button(
                    enabled = stock > 0,

                    onClick = {

                        stock--

                        viewModel.actualizarStock(
                            it.id,
                            stock
                        )
                    }
                ) {
                    Text("-1")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text("Guardar y Volver")
            }
        }
    }
}