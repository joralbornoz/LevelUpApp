package com.levelup.data

data class Sucursal(
    val id: Int,
    val nombre: String,
    val direccion: String,
    val horario: String,
    val telefono: String
)

val DataSucursales = listOf(
    Sucursal(
        id = 1,
        nombre = "LevelUp Plaza Egaña",
        direccion = "Av. Larraín 5862, La Reina, Santiago",
        horario = "Lun a Dom 10:00 - 20:00",
        telefono = "+56 9 1234 5678"
    ),
    Sucursal(
        id = 2,
        nombre = "LevelUp Maipú",
        direccion = "Av. Pajaritos 3456, Maipú, Santiago",
        horario = "Lun a Sáb 09:00 - 19:00",
        telefono = "+56 2 2123 4455"
    ),
    Sucursal(
        id = 3,
        nombre = "LevelUp Viña del Mar",
        direccion = "Av. Libertad 123, Viña del Mar",
        horario = "Lun a Dom 10:00 - 20:00",
        telefono = "+56 9 9988 7766"
    )
)
