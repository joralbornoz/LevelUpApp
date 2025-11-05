package com.levelup.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.levelup.data.DatosUsuario
import com.levelup.data.DataProductos

class AppViewModel(app: Application): AndroidViewModel(app) {
    private val prefs = DatosUsuario(app)

    private val _cart = MutableStateFlow<List<Long>>(emptyList())
    val cart: StateFlow<List<Long>> = _cart

    val nombreUsuario: StateFlow<String> = prefs.nombreUsuario.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5_000), ""
    )
    val emailUsuario: StateFlow<String> = prefs.emailUsuario.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5_000), ""
    )

    // 👇 Estado de sesión derivado (logueado si hay nombre)
    val estaLogueado: StateFlow<Boolean> = nombreUsuario
        .map { it.isNotBlank() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    init {
        viewModelScope.launch {
            prefs.carritoIds.collect { csv ->
                _cart.value = if (csv.isBlank()) emptyList()
                else csv.split(",").mapNotNull { it.toLongOrNull() }
            }
        }
    }

    fun addToCart(id: Long) { _cart.value = _cart.value + id; persistCart() }
    fun clearCart() { _cart.value = emptyList(); persistCart() }
    private fun persistCart() = viewModelScope.launch { prefs.guardarCarrito(_cart.value.joinToString(",")) }

    fun guardarPerfil(nombre: String, email: String) = viewModelScope.launch {
        prefs.guardarUsuario(nombre, email)
    }

    // 👇 Logout (borra usuario y carrito)
    fun logout() = viewModelScope.launch {
        prefs.guardarUsuario("", "")
        prefs.guardarCarrito("")
        _cart.value = emptyList()
    }

    fun total(): Int = _cart.value.sumOf { id ->
        DataProductos.firstOrNull { it.id == id }?.precio ?: 0
    }
}
