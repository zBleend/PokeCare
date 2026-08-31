package cl.ejercicio.model

import java.time.LocalDateTime

class PokemonElectrico(
    idPokedex: String,
    nombrePokemon: String,
    tipoEntrenador: TipoEntrenador,
    fechaIngreso: LocalDateTime
) : PokemonModel(idPokedex, nombrePokemon, TipoPokemon.ELECTRICO, tipoEntrenador, fechaIngreso) {

    override fun calcularCosto(tiempoMinutos: Int): Double {
        val horas = tiempoMinutos / 60.0
        var costo = horas * 1500.0

        if (tipoEntrenador == TipoEntrenador.VIP) {
            costo *= 0.80
        }

        return costo
    }
}
