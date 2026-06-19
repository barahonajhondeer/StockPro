package com.example.stockpro.data

data class Producto(
    val id:Int,
    val nombre:String,
    val descripcion:String,
    val precio:Double,
    val stockActual:Int
)