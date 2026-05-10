package com.example.libroapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.libroapp.model.Libro
import com.example.libroapp.ui.form.LibroFormScreen
import com.example.libroapp.ui.home.LibroHomeScreen
import com.example.libroapp.ui.list.LibroListScreen

/**
 * Grafo de navegación principal.
 */
@Composable
fun LibroNavGraph(
    navController: NavHostController,
    libros: List<Libro>,
    onAgregarLibro: (Libro) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = LibroDestino.Inicio.ruta
    ) {
        // Pantalla 1 - Inicio
        composable(route = LibroDestino.Inicio.ruta) {
            LibroHomeScreen(
                totalLibros = libros.size,
                onNavigateToIngreso = {
                    navController.navigate(LibroDestino.IngresoLibro.ruta)
                },
                onNavigateToConsulta = {
                    navController.navigate(LibroDestino.ConsultaLibros.ruta)
                }
            )
        }

        // Pantalla 2 - Formulario de ingreso
        composable(route = LibroDestino.IngresoLibro.ruta) {
            LibroFormScreen(
                onGuardarLibro = { nuevoLibro ->
                    onAgregarLibro(nuevoLibro)
                    navController.popBackStack()
                },
                onNavigateBack = {
                    navController.popBackStack()
                },
                proximoId = (libros.maxOfOrNull { it.id } ?: 0) + 1
            )
        }

        // Pantalla 3 - Consulta / listado
        composable(route = LibroDestino.ConsultaLibros.ruta) {
            LibroListScreen(
                libros = libros,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToIngreso = {
                    navController.navigate(LibroDestino.IngresoLibro.ruta)
                }
            )
        }
    }
}
