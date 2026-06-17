package com.example.libroapp.data

data class Libro(
    val id: Int,
    val titulo: String,
    val autor: String,
    val anioPublicacion: Int,
    val sinopsis: String,
    val imageUrl: String
)

fun librosDummyIniciales() = listOf(
    Libro(
        id = 1,
        titulo = "El Principito",
        autor = "Antoine de Saint-Exupéry",
        anioPublicacion = 1943,
        sinopsis = "Esta es la historia completa y detallada de un pequeño príncipe que parte de su asteroide a una travesía por el universo, en la cual descubre la extraña forma en que los adultos ven la vida y comprende el valor del amor y la amistad.",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgaY8wpnEJYxCDBzvDunSbMuuih3TJIKPGz1xI0mdZiXg-oqJub1NpMbQWGLrdGWXu_yDp8-Yj9suGQ8FRnecPaOSZlQ2j-5GIAAwarBx6BQ&s=10"
    ),
    Libro(
        id = 2,
        titulo = "Cien años de soledad",
        autor = "Gabriel García Márquez",
        anioPublicacion = 1967,
        sinopsis = "La épica historia de la familia Buendía a lo largo de siete generaciones en el pueblo ficticio de Macondo.",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwPeewtKLyDNztXSOYwQ2OAUDcclvRT1cvpsQcweOADXuD05IkNfYi5m5SEkL82GTzduLFG1D1VFeuyHAsZHw0u0Zbl8aDCPR8qYUurTdMGw&s=10"
    ),
    Libro(
        id = 3,
        titulo = "Don Quijote de la Mancha",
        autor = "Miguel de Cervantes",
        anioPublicacion = 1605,
        sinopsis = "Alonso Quijano es un hidalgo pobre que de tanto leer novelas de caballería acaba enloqueciendo y creyendo ser un caballero andante.",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQAQuAvfgZ5RMWrs0XEvZB1YiTxpkG6JiJthox0cOY1xARDKW9lBpu6zmPXziSQrEpDP4BNXnkkmXGcR10GD2hOASIiWokVA_DEihf5dJ6FEA&s=10"
    )
)
