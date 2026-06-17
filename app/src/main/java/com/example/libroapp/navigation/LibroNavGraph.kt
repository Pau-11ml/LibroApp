package com.example.libroapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.libroapp.ui.LibroFormScreen
import com.example.libroapp.ui.LibroHomeScreen
import com.example.libroapp.ui.LibroListScreen
import com.example.libroapp.ui.LibroViewModel

@Composable
fun LibroNavGraph(
    navController: NavHostController,
    libroViewModel: LibroViewModel = viewModel()
) {
    val uiState by libroViewModel.uiState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = LibroDestino.Inicio.ruta
    ) {
        composable(route = LibroDestino.Inicio.ruta) {
            LibroHomeScreen(
                onNavigateToForm = {
                    libroViewModel.resetForm()
                    navController.navigate(LibroDestino.IngresoLibro.ruta)
                },
                onNavigateToList = {
                    navController.navigate(LibroDestino.ConsultaLibros.ruta)
                }
            )
        }

        composable(route = LibroDestino.IngresoLibro.ruta) {
            LibroFormScreen(
                viewModel = libroViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = LibroDestino.ConsultaLibros.ruta) {
            LibroListScreen(
                viewModel = libroViewModel,
                onAddLibroClick = {
                    libroViewModel.resetForm()
                    navController.navigate(LibroDestino.IngresoLibro.ruta)
                }
            )
        }
    }
}
