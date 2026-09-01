package cl.ejercicio.model

class CamillaModel (

    val numero: Int,
    var estado: EstadoCamilla = EstadoCamilla.LIBRE,
    var pokemon: PokemonModel? = null,
    var motivo: String = ""

)