package com.example.libroapp.navigation

sealed class LibroDestino(val ruta: String) {
    data object Inicio : LibroDestino("inicio")
    data object IngresoLibro : LibroDestino("ingreso_libro")
    data object ConsultaLibros : LibroDestino("consulta_libros")
}
