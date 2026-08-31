package cl.ejercicio.model

import java.time.LocalDateTime

class PokemonDragon(
    idPokedex: String,
    nombrePokemon: String,
    tipoEntrenador: TipoEntrenador,
    fechaIngreso: LocalDateTime,
    val megaEvolucionado: Boolean
) : PokemonModel(idPokedex, nombrePokemon, TipoPokemon.DRAGON, tipoEntrenador, fechaIngreso){

    override fun calcularCosto(tiempoMinutos: Int): Double {
        val horas = tiempoMinutos /60.0
        var costo = horas * 2500.0

        if (megaEvolucionado) {
            costo *= 1.30
        }

        return costo

    }

}
