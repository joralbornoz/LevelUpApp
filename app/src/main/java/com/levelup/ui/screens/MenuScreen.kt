package com.levelup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.levelup.ui.theme.*
import com.levelup.ui.viewmodel.AppViewModel
import com.levelup.navigation.NavRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(onNavigate: (String) -> Unit) {
    val vm: AppViewModel = viewModel()
    val name by vm.nombreUsuario.collectAsState()
    val email by vm.emailUsuario.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("LevelUp — Menú") },
                actions = {
                    IconButton(onClick = { vm.logout() }) {
                        Icon(Icons.Default.Logout, contentDescription = "Salir")
                    }
                }
            )
        }
    ) { inner ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Saludo con datos de login
            Card(
                colors = CardDefaults.cardColors(containerColor = CardBlack, contentColor = TextPrimary),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Hola, $name 👋", style = MaterialTheme.typography.titleLarge, color = SecondaryBlue)
                    Text(email, style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
                }
            }

            MenuButton("Productos / Tienda", Icons.Filled.Store) { onNavigate(NavRoutes.Productos) }
            MenuButton("Carrito", Icons.Filled.ShoppingCart) { onNavigate(NavRoutes.Carrito) }
            MenuButton("Perfil", Icons.Filled.Person) { onNavigate(NavRoutes.Perfil) }
            MenuButton("Sucursales", Icons.Filled.LocationOn) { onNavigate(NavRoutes.Sucursales) }
        }
    }
}

@Composable
private fun MenuButton(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = MaterialTheme.shapes.extraLarge,
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, contentColor = DarkBackground),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
    ) {
        Icon(icon, contentDescription = null)
        Spacer(Modifier.width(10.dp))
        Text(text, fontWeight = FontWeight.SemiBold)
    }
}
