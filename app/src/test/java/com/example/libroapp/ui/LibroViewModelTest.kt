package com.example.libroapp.ui

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LibroViewModelTest {

    private val viewModel = LibroViewModel()

    @Test
    fun libroViewModel_Initialization_FirstThreeLibrosLoaded() {
        val uiState = viewModel.uiState.value
        assertEquals(3, uiState.libros.size)
        assertFalse(uiState.mostrarError)
    }

    @Test
    fun libroViewModel_AddLibro_UpdatesUiStateAndList() {
        viewModel.updateTitulo("Test Title")
        viewModel.updateAutor("Test Author")
        viewModel.updateAnio("2024")
        
        viewModel.agregarLibro()
        
        val uiState = viewModel.uiState.value
        assertEquals(4, uiState.libros.size)
        assertEquals("Test Title", uiState.libros.last().titulo)
        assertEquals("", uiState.titulo) // Fields should be reset
    }

    @Test
    fun libroViewModel_EmptyFields_ShowsError() {
        viewModel.updateTitulo("")
        viewModel.agregarLibro()
        
        val uiState = viewModel.uiState.value
        assertTrue(uiState.mostrarError)
        assertEquals(3, uiState.libros.size) // Size should not change
    }
}
