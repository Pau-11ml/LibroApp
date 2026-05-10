package com.example.libroapp.ui.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.libroapp.R
import com.example.libroapp.model.Libro
import com.example.libroapp.model.librosDummyIniciales
import com.example.libroapp.ui.components.LibroItem
import com.example.libroapp.ui.theme.LibrosAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibroListScreen(
    libros: List<Libro>,
    onNavigateBack: () -> Unit,
    onNavigateToIngreso: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.list_title)) },
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
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onNavigateToIngreso,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                text = { Text("Nuevo") },
                icon = { Icon(Icons.Default.Add, contentDescription = null) }
            )
        }
    ) { innerPadding ->
        LibroListContent(
            libros = libros,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun LibroListContent(
    libros: List<Libro>,
    modifier: Modifier = Modifier
) {
    if (libros.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.empty_list_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.empty_list_subtitle),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(
                items = libros,
                key = { libro -> libro.id }
            ) { libro ->
                LibroItem(libro = libro)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LibroListScreenPreview() {
    LibrosAppTheme {
        LibroListScreen(
            libros = librosDummyIniciales(),
            onNavigateBack = {},
            onNavigateToIngreso = {}
        )
    }
}
