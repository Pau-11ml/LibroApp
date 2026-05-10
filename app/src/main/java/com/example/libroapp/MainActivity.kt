package com.example.libroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.libroapp.model.librosDummyIniciales
import com.example.libroapp.navigation.LibroNavGraph
import com.example.libroapp.ui.theme.LibrosAppTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibrosAppTheme {
                LibroApp()
            }
        }
    }
}

@Composable
fun LibroApp() {
    val navController = rememberNavController()

    // Estado global de la lista de libros (State Hoisting)
    var libros by remember { mutableStateOf(librosDummyIniciales()) }

    LibroNavGraph(
        navController = navController,
        libros = libros,
        onAgregarLibro = { nuevoLibro ->
            libros = libros + nuevoLibro
        }
    )
}
