package com.levelup.data

data class Productos(
    val id: Long,
    val nombre: String,
    val precio: Int,
    val descripcion: String
)

val DataProductos = listOf(
    Productos(1, "Mouse Gamer RGB", 29990, "Sensor 8000 DPI, luces RGB"),
    Productos(2, "Teclado Mecánico", 79990, "Switch Red, base metálica"),
    Productos(3, "Auriculares 7.1", 49990, "Micrófono retráctil, sonido 7.1"),
    Productos(4, "Playstation 5", 649000, "Consola Playstation 5 "),
)
