package cl.ejercicio

import cl.ejercicio.model.*
import cl.ejercicio.service.CentroPokemon
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

fun main() = runBlocking {

    val centro = CentroPokemon()

    val pikachu = PokemonElectrico(
        idPokedex = "PK1001",
        nombrePokemon = "Pikachu",
        tipoEntrenador = TipoEntrenador.VIP,
        fechaIngreso = LocalDateTime.now()
    )

    val raichu = PokemonElectrico(
        idPokedex = "PK1002",
        nombrePokemon = "Raichu",
        tipoEntrenador = TipoEntrenador.NOVATO,
        fechaIngreso = LocalDateTime.now()
    )

    val squirtle = PokemonAgua(
        idPokedex = "PK2001",
        nombrePokemon = "Squirtle",
        tipoEntrenador = TipoEntrenador.NOVATO,
        fechaIngreso = LocalDateTime.now()
    )

    val dragonite = PokemonDragon(
        idPokedex = "PK3001",
        nombrePokemon = "Dragonite",
        tipoEntrenador = TipoEntrenador.LEGENDARIO,
        fechaIngreso = LocalDateTime.now(),
        megaEvolucionado = true
    )

    val altaria = PokemonDragon(
        idPokedex = "PK3002",
        nombrePokemon = "Altaria",
        tipoEntrenador = TipoEntrenador.NOVATO,
        fechaIngreso = LocalDateTime.now(),
        megaEvolucionado = false
    )

    println("=== INGRESANDO POKÉMON ===")
    centro.ingresarPokemon(pikachu)
    centro.ingresarPokemon(raichu)
    centro.ingresarPokemon(squirtle)
    centro.ingresarPokemon(dragonite)
    centro.ingresarPokemon(altaria)

    println("\n=== PRUEBA DE ERROR ===")
    val pokemonInvalido = PokemonElectrico(
        idPokedex = "123ABC",
        nombrePokemon = "Test",
        tipoEntrenador = TipoEntrenador.NOVATO,
        fechaIngreso = LocalDateTime.now()
    )
    centro.ingresarPokemon(pokemonInvalido)

    println("\n=== PROCESANDO ALTAS ===")
    centro.darDeAlta("PK1001")
    centro.darDeAlta("PK1002")
    centro.darDeAlta("PK2001")
    centro.darDeAlta("PK3001")
    centro.darDeAlta("PK3002")

    println("\n")
    centro.generarReporte()
}
