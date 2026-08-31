package cl.ejercicio.model

import java.time.LocalDateTime

open class PokemonModel (

    val idPokedex: String,
    val nombrePokemon: String,
    val tipoPokemon: TipoPokemon,
    val tipoEntrenador: TipoEntrenador,
    val fechaIngreso: LocalDateTime

) {
    open fun calcularCosto(tiempoMinutos: Int): Double {
        return 0.0
    }

}