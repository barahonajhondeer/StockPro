package com.example.stockpro.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.stockpro.data.Producto

class StockViewModel : ViewModel() {

    private val _productos = mutableStateListOf(
        Producto(1, "Router WiFi", "Router TP-Link AX1800", 85.0, 3),
        Producto(2, "Switch Red", "Switch 24 puertos", 120.0, 7),
        Producto(3, "Cable UTP", "Cable Cat6 10 metros", 12.0, 15),
        Producto(4, "Access Point", "Punto de acceso empresarial", 150.0, 2),
        Producto(5, "Rack Servidor", "Rack 42U", 450.0, 1),
        Producto(6, "UPS", "Respaldo de energía 1500VA", 280.0, 0)
    )

    val productos: List<Producto>
        get() = _productos

    fun obtenerProducto(id: Int): Producto? {
        return _productos.find { it.id == id }
    }

    fun actualizarStock(id: Int, nuevaCantidad: Int) {
        val index = _productos.indexOfFirst { it.id == id }

        if (index != -1) {
            val producto = _productos[index]
            _productos[index] =
                producto.copy(stockActual = nuevaCantidad)
        }
    }

    fun obtenerProductosEnRiesgo(): List<Producto> {
        return _productos.filter { it.stockActual < 5 }
    }

    fun calcularValorTotalInventario(): Double {
        return _productos.sumOf {
            it.precio * it.stockActual
        }
    }

    fun productosAgotados(): Int {
        return _productos.count {
            it.stockActual == 0
        }
    }
}