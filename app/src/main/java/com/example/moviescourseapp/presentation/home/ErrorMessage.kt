package com.example.moviescourseapp.presentation.home

enum class ErrorMessage(val message: String) {
    INTERNET_CONNECTION ("Ocurrió un error, veririca tu conexión a internet"),
    DATABASE_ERROR("Ocurrio un error inesperado en la base de datos"),
    DEFAULT("Ocurrió un error desconocido")
}