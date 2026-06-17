package com.example.libroapp.ui

import com.example.libroapp.data.Libro


/**
 * Data class that represents the game UI state
 */
data class LibroUiState(
    val libros: List<Libro> = emptyList(),
    val titulo: String = "",
    val autor: String = "",
    val anio: String = "",
    val sinopsis: String = "",
    val imageUrl: String = "",
    val mostrarError: Boolean = false
)
