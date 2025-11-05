package com.levelup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SucursalesScreen(onBack: () -> Unit) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("Sucursales") },
            navigationIcon = { IconButton(onClick = onBack){ Icon(Icons.Default.ArrowBack, "Volver") } }
        )
    }) { inner ->
        Column(Modifier.padding(inner).padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Centro — Av. Principal 123")
            Text("Norte — Av. Norte 45")
            Text("Sur — Calle Sur 789")
        }
    }
}
