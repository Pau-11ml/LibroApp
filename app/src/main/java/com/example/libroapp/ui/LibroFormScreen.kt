package com.example.libroapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.libroapp.R
import com.example.libroapp.ui.theme.LibrosAppTheme

@Composable
fun LibroFormScreen(
    viewModel: LibroViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LibroFormContent(
        uiState = uiState,
        onTituloChange = { viewModel.updateTitulo(it) },
        onAutorChange = { viewModel.updateAutor(it) },
        onAnioChange = { viewModel.updateAnio(it) },
        onSinopsisChange = { viewModel.updateSinopsis(it) },
        onImageUrlChange = { viewModel.updateImageUrl(it) },
        onGuardar = { 
            viewModel.agregarLibro()
            if (!uiState.mostrarError) {
                onNavigateBack()
            }
        },
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibroFormContent(
    uiState: LibroUiState,
    onTituloChange: (String) -> Unit,
    onAutorChange: (String) -> Unit,
    onAnioChange: (String) -> Unit,
    onSinopsisChange: (String) -> Unit,
    onImageUrlChange: (String) -> Unit,
    onGuardar: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
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
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = uiState.titulo,
                onValueChange = onTituloChange,
                label = { Text(stringResource(R.string.label_titulo)) },
                placeholder = { Text(stringResource(R.string.placeholder_titulo)) },
                isError = uiState.mostrarError && uiState.titulo.isBlank(),
                supportingText = {
                    if (uiState.mostrarError && uiState.titulo.isBlank()) {
                        Text(stringResource(R.string.error_titulo_obligatorio))
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = uiState.autor,
                onValueChange = onAutorChange,
                label = { Text(stringResource(R.string.label_autor)) },
                placeholder = { Text(stringResource(R.string.placeholder_autor)) },
                isError = uiState.mostrarError && uiState.autor.isBlank(),
                supportingText = {
                    if (uiState.mostrarError && uiState.autor.isBlank()) {
                        Text(stringResource(R.string.error_autor_obligatorio))
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = uiState.anio,
                onValueChange = onAnioChange,
                label = { Text(stringResource(R.string.label_anio)) },
                placeholder = { Text(stringResource(R.string.placeholder_anio)) },
                isError = uiState.mostrarError && uiState.anio.isBlank(),
                supportingText = {
                    if (uiState.mostrarError && uiState.anio.isBlank()) {
                        Text(stringResource(R.string.error_anio_obligatorio))
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = uiState.sinopsis,
                onValueChange = onSinopsisChange,
                label = { Text(stringResource(R.string.label_sinopsis)) },
                placeholder = { Text(stringResource(R.string.placeholder_sinopsis)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                maxLines = 4
            )

            OutlinedTextField(
                value = uiState.imageUrl,
                onValueChange = onImageUrlChange,
                label = { Text(stringResource(R.string.label_image_url)) },
                placeholder = { Text(stringResource(R.string.placeholder_image_url)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                supportingText = { Text("Pega un enlace directo a una imagen (jpg, png)") }
            )

            if (uiState.imageUrl.isNotBlank()) {
                Text(
                    text = "Vista previa de la imagen:",
                    style = MaterialTheme.typography.labelMedium
                )
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(uiState.imageUrl)
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
}

@Preview(showBackground = true)
@Composable
fun LibroFormPreview() {
    LibrosAppTheme {
        LibroFormContent(
            uiState = LibroUiState(titulo = "El Principito", autor = "Antoine"),
            onTituloChange = {},
            onAutorChange = {},
            onAnioChange = {},
            onSinopsisChange = {},
            onImageUrlChange = {},
            onGuardar = {},
            onNavigateBack = {}
        )
    }
}
