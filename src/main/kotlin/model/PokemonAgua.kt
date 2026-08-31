package cl.ejercicio.model

import java.time.LocalDateTime

class PokemonAgua(
    idPokedex: String,
    nombrePokemon: String,
    tipoEntrenador: TipoEntrenador,
    fechaIngreso: LocalDateTime
) : PokemonModel(idPokedex, nombrePokemon, TipoPokemon.AGUA, tipoEntrenador, fechaIngreso){

    override fun calcularCosto(tiempoMinutos: Int): Double {

        if (tiempoMinutos < 30) {
            return 0.0
        }

        val horas = tiempoMinutos / 60.0
        return horas * 800.0

    }

}
