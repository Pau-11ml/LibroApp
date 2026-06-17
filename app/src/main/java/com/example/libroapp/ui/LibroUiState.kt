package com.example.libroapp.ui

import com.example.libroapp.data.Libro
import com.example.libroapp.data.librosDummyIniciales

/**
 * Data class that represents the game UI state
 */
data class LibroUiState(
    val libros: List<Libro> = librosDummyIniciales(),
    val titulo: String = "",
    val autor: String = "",
    val anio: String = "",
    val sinopsis: String = "",
    val imageUrl: String = "",
    val mostrarError: Boolean = false
)
