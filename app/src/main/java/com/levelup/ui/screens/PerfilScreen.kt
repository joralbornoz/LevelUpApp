package com.levelup.ui.screens

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.levelup.ui.viewmodel.AppViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onBack: () -> Unit) {
    val vm: AppViewModel = viewModel()
    val nombreUsuario by vm.nombreUsuario.collectAsState()
    val emailUsuario by vm.emailUsuario.collectAsState()
    val direccionUsuario by vm.direccionUsuario.collectAsState()

    var editarNombre by remember(nombreUsuario) { mutableStateOf(nombreUsuario) }
    var editarEmail by remember(emailUsuario) { mutableStateOf(emailUsuario) }
    var editarDireccion by remember(direccionUsuario) { mutableStateOf(direccionUsuario) }
    var photo by remember { mutableStateOf<Bitmap?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bmp ->
        photo = bmp
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("Perfil Usuario") },
            navigationIcon = { IconButton(onClick = onBack){ Icon(Icons.Default.ArrowBack, "Volver") } }
        )
    }) { inner ->
        Column(Modifier.padding(inner).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            photo?.let { Image(it.asImageBitmap(), contentDescription = null, modifier = Modifier.size(120.dp)) }
            Button(onClick = { cameraLauncher.launch(null) }) { Text("Tomar foto") }

            OutlinedTextField(
                value = editarNombre,
                onValueChange = { editarNombre = it },
                label = { Text("Nombre") },
                singleLine = true
            )
            OutlinedTextField(value = editarEmail,
                onValueChange = { editarEmail = it },
                label = { Text("Email") },
                singleLine = true
            )
            OutlinedTextField(value = editarDireccion,
                onValueChange = { editarDireccion = it },
                label = { Text("Direccion") },
                singleLine = true
            )

            Button(onClick = { vm.guardarPerfil(editarNombre, editarEmail, editarDireccion) }) { Text("Guardar") }
            Text(
                "Guardado: $nombreUsuario • $emailUsuario • $editarDireccion",
                style = MaterialTheme.typography.bodyMedium)
        }
    }
}
