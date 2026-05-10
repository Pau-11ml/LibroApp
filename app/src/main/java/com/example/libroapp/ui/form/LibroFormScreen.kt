package com.example.libroapp.ui.form

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.libroapp.R
import com.example.libroapp.model.Libro
import com.example.libroapp.ui.theme.LibrosAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibroFormScreen(
    onGuardarLibro: (Libro) -> Unit,
    onNavigateBack: () -> Unit,
    proximoId: Int
) {
    var titulo by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var sinopsis by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    var mostrarError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.form_title)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_description)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LibroFormContent(
            titulo = titulo,
            autor = autor,
            anio = anio,
            sinopsis = sinopsis,
            imageUrl = imageUrl,
            mostrarError = mostrarError,
            onTituloChange = { titulo = it },
            onAutorChange = { autor = it },
            onAnioChange = { anio = it },
            onSinopsisChange = { sinopsis = it },
            onImageUrlChange = { imageUrl = it },
            onGuardar = {
                if (titulo.isBlank() || autor.isBlank() || anio.isBlank()) {
                    mostrarError = true
                } else {
                    val nuevoLibro = Libro(
                        id = proximoId,
                        titulo = titulo.trim(),
                        autor = autor.trim(),
                        anioPublicacion = anio.trim().toIntOrNull() ?: 0,
                        sinopsis = sinopsis.trim(),
                        imageUrl = imageUrl.trim()
                    )
                    onGuardarLibro(nuevoLibro)
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun LibroFormContent(
    titulo: String,
    autor: String,
    anio: String,
    sinopsis: String,
    imageUrl: String,
    mostrarError: Boolean,
    onTituloChange: (String) -> Unit,
    onAutorChange: (String) -> Unit,
    onAnioChange: (String) -> Unit,
    onSinopsisChange: (String) -> Unit,
    onImageUrlChange: (String) -> Unit,
    onGuardar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = titulo,
            onValueChange = onTituloChange,
            label = { Text(stringResource(R.string.label_titulo)) },
            placeholder = { Text(stringResource(R.string.placeholder_titulo)) },
            isError = mostrarError && titulo.isBlank(),
            supportingText = {
                if (mostrarError && titulo.isBlank()) {
                    Text(stringResource(R.string.error_titulo_obligatorio))
                }
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = autor,
            onValueChange = onAutorChange,
            label = { Text(stringResource(R.string.label_autor)) },
            placeholder = { Text(stringResource(R.string.placeholder_autor)) },
            isError = mostrarError && autor.isBlank(),
            supportingText = {
                if (mostrarError && autor.isBlank()) {
                    Text(stringResource(R.string.error_autor_obligatorio))
                }
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = anio,
            onValueChange = { if (it.all { c -> c.isDigit() } && it.length <= 4) onAnioChange(it) },
            label = { Text(stringResource(R.string.label_anio)) },
            placeholder = { Text(stringResource(R.string.placeholder_anio)) },
            isError = mostrarError && anio.isBlank(),
            supportingText = {
                if (mostrarError && anio.isBlank()) {
                    Text(stringResource(R.string.error_anio_obligatorio))
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = sinopsis,
            onValueChange = onSinopsisChange,
            label = { Text(stringResource(R.string.label_sinopsis)) },
            placeholder = { Text(stringResource(R.string.placeholder_sinopsis)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            maxLines = 4
        )

        OutlinedTextField(
            value = imageUrl,
            onValueChange = onImageUrlChange,
            label = { Text(stringResource(R.string.label_image_url)) },
            placeholder = { Text(stringResource(R.string.placeholder_image_url)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            supportingText = { Text("Pega un enlace directo a una imagen (jpg, png)") }
        )

        if (imageUrl.isNotBlank()) {
            Text(
                text = "Vista previa de la imagen:",
                style = MaterialTheme.typography.labelMedium
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Vista previa",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Fit,
                error = painterResource(R.drawable.ic_launcher_foreground),
                placeholder = painterResource(R.drawable.ic_launcher_foreground)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onGuardar,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Text(stringResource(R.string.btn_guardar_libro), style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LibroFormPreview() {
    LibrosAppTheme {
        LibroFormContent(
            titulo = "El Principito",
            autor = "Antoine de Saint-Exupéry",
            anio = "1943",
            sinopsis = "Un aviador perdido...",
            imageUrl = "",
            mostrarError = false,
            onTituloChange = {},
            onAutorChange = {},
            onAnioChange = {},
            onSinopsisChange = {},
            onImageUrlChange = {},
            onGuardar = {}
        )
    }
}
