package com.example.stockpro.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.stockpro.data.Producto

class StockViewModel : ViewModel() {

    private val _productos = mutableStateListOf(
        Producto(1, "Laptop", "Laptop Dell i7", 1200.0, 4),
        Producto(2, "Mouse", "Mouse Logitech", 25.0, 10),
        Producto(3, "Teclado", "Teclado Mecánico", 60.0, 2),
        Producto(4, "Monitor", "Monitor Samsung 24", 220.0, 6),
        Producto(5, "Impresora", "HP Multifuncional", 180.0, 0),
        Producto(6, "SSD", "SSD Kingston 1TB", 90.0, 8)
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