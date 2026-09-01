package cl.ejercicio.model

sealed class EstadoCamilla {

    object Libre : EstadoCamilla()
    data class Ocupada(val pokemon: PokemonModel) : EstadoCamilla()
    data class EnProceso(val motivo: String) : EstadoCamilla()
    data class FueraDeServicio(val motivo: String) : EstadoCamilla()

}