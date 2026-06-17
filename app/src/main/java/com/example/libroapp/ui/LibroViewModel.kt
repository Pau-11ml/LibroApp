package com.example.libroapp.ui

import androidx.lifecycle.ViewModel
import com.example.libroapp.data.Libro
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.libroapp.data.librosDummyIniciales

/**
 * ViewModel containing the app data and methods to process the data
 */
class LibroViewModel : ViewModel() {

    // Game UI state
    private val _uiState = MutableStateFlow(
        LibroUiState(
            libros = librosDummyIniciales()
            )
    )
    val uiState: StateFlow<LibroUiState> = _uiState.asStateFlow()

    fun updateTitulo(titulo: String) {
        _uiState.update { it.copy(titulo = titulo) }
    }

    fun updateAutor(autor: String) {
        _uiState.update { it.copy(autor = autor) }
    }

    fun updateAnio(anio: String) {
        if (anio.all { it.isDigit() } && anio.length <= 4) {
            _uiState.update { it.copy(anio = anio) }
        }
    }

    fun updateSinopsis(sinopsis: String) {
        _uiState.update { it.copy(sinopsis = sinopsis) }
    }

    fun updateImageUrl(imageUrl: String) {
        _uiState.update { it.copy(imageUrl = imageUrl) }
    }

    fun agregarLibro() {
        val currentState = _uiState.value
        if (currentState.titulo.isBlank() || currentState.autor.isBlank() || currentState.anio.isBlank()) {
            _uiState.update { it.copy(mostrarError = true) }
        } else {
            val nuevoLibro = Libro(
                id = (currentState.libros.maxOfOrNull { it.id } ?: 0) + 1,
                titulo = currentState.titulo.trim(),
                autor = currentState.autor.trim(),
                anioPublicacion = currentState.anio.trim().toIntOrNull() ?: 0,
                sinopsis = currentState.sinopsis.trim(),
                imageUrl = currentState.imageUrl.trim()
            )
            _uiState.update { 
                it.copy(
                    libros = it.libros + nuevoLibro,
                    titulo = "",
                    autor = "",
                    anio = "",
                    sinopsis = "",
                    imageUrl = "",
                    mostrarError = false
                )
            }
        }
    }
    
    fun resetForm() {
        _uiState.update { 
            it.copy(
                titulo = "",
                autor = "",
                anio = "",
                sinopsis = "",
                imageUrl = "",
                mostrarError = false
            )
        }
    }
}
