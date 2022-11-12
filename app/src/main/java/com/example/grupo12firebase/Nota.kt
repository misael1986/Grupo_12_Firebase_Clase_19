package com.example.grupo12firebase

data class Nota(val nombre: String = "",
                val asignatura: String = "",
                val nota: Double = 0.0) {

    override fun toString(): String{
        return (nombre + "\t" + asignatura + "\t" + nota)
    }
}