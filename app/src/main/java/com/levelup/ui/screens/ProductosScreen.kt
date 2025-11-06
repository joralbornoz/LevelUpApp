package com.levelup.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.levelup.navigation.NavRoutes
import com.levelup.data.DataProductos
import com.levelup.ui.viewmodel.AppViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosScreen(onBack: () -> Unit, onNavigate: (String) -> Unit) {
    val vm: AppViewModel = viewModel()
    val carrito by vm.carrito.collectAsState()
    val cantidad = carrito.size

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Tienda LevelUp") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Volver") }
                }
            )
        }
    ) { inner ->
        Column(
            Modifier.fillMaxSize().padding(inner).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(onClick = { onNavigate(NavRoutes.Carrito) }) {
                Text("Ir al carrito ($cantidad)")
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(DataProductos) { p ->
                    Card(Modifier.fillMaxWidth()) {
                        Column(
                            Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                p.nombre,
                                style = MaterialTheme.typography.titleLarge)
                            Text(
                                "CLP ${p.precio}",
                                style = MaterialTheme.typography.bodyLarge)
                            Text(
                                p.descripcion, style = MaterialTheme.typography.bodyMedium)
                            Button(onClick = { vm.addToCart(p.id) }, modifier = Modifier.padding(top = 8.dp)) {
                                Text("Agregar")
                            }
                        }
                    }
                }
            }
        }
    }
}
