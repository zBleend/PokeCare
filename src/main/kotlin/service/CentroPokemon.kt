package cl.ejercicio.service

import cl.ejercicio.model.CamillaModel
import cl.ejercicio.model.EstadoCamilla
import cl.ejercicio.model.FichaAlta
import cl.ejercicio.model.PokemonModel
import cl.ejercicio.model.TipoEntrenador
import cl.ejercicio.model.TipoPokemon
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class CentroPokemon {
    val nombre = "PokeCare Kanto Centro"
    val capacidad = 10
    val camillas = mutableListOf<CamillaModel>()
    var contadorIds = 0
    var contadorFichas = 0
    val historial = mutableListOf<PokemonModel>()
    val fichasDeAlta = mutableListOf<FichaAlta>()
    var recaudacionTotal = 0.0
    val recaudacionPorCategoria = mutableMapOf<TipoPokemon, Double>()

    init {
        for (i in 1..capacidad) {
            camillas.add(CamillaModel(numero = i))
        }
    }

    fun generarId(): String {
        contadorIds++
        return "PK" + contadorIds.toString().padStart(4, '0')
    }

    fun validarCodigo(codigo: String): Boolean {
        val regex = Regex("^PK\\d{4}$")
        return regex.matches(codigo)
    }

    fun buscarCamillaLibre(): CamillaModel? {
        return camillas.firstOrNull {
            it.estado is EstadoCamilla.Libre
        }
    }

    suspend fun ingresarPokemon(pokemon: PokemonModel) {
        if (!validarCodigo(pokemon.idPokedex)) {
            println("Error: Codigo Invalido - ${pokemon.idPokedex}")
            return
        }

        val camilla = buscarCamillaLibre()
        if (camilla == null) {
            println("Error: centro sin capacidad")
            return
        }

        camilla.estado = EstadoCamilla.EnProceso(motivo = "Ingresando Pokemon...")
        println("Camilla ${camilla.numero} - Ingresando ${pokemon.nombrePokemon}...")

        delay(3000)

        camilla.estado = EstadoCamilla.Ocupada(pokemon = pokemon)
        historial.add(pokemon)

        println("${pokemon.nombrePokemon} ingresado en camilla ${camilla.numero}")
    }

    suspend fun darDeAlta(codigo: String){
        if (!validarCodigo(codigo)) {
            println("Error: codigo Invalido - ${codigo}")
            return
        }

        val camilla = camillas.find { estado ->
            estado.estado is EstadoCamilla.Ocupada &&
            (estado.estado as EstadoCamilla.Ocupada).pokemon.idPokedex == codigo
        }

        if (camilla == null || camilla.estado !is EstadoCamilla.Ocupada) {
            println("Error: pokemon no encontrado - $codigo")
            return
        }

        val pokemon = (camilla.estado as EstadoCamilla.Ocupada).pokemon

        camilla.estado = EstadoCamilla.EnProceso(motivo = "Procesando alta y cobro...")
        println("Camilla ${camilla.numero} - Procesando alta de ${pokemon.nombrePokemon}...")

        delay(6500)

        val tiempoMinutos = ChronoUnit.MINUTES.between(pokemon.fechaIngreso, LocalDateTime.now()).toInt()
        val costoBase = pokemon.calcularCosto(tiempoMinutos)
        val conIVA = costoBase * 1.19
        val total = if (pokemon.tipoEntrenador == TipoEntrenador.LEGENDARIO) conIVA * 0.50 else conIVA

        recaudacionTotal += total
        recaudacionPorCategoria[pokemon.tipoPokemon] = (recaudacionPorCategoria[pokemon.tipoPokemon] ?: 0.0) + total

        contadorFichas++
        val ficha = FichaAlta(
            numeroFicha = contadorFichas,
            pokemon = pokemon,
            tiempoMinutos = tiempoMinutos,
            montoPagado = total
        )
        fichasDeAlta.add(ficha)

        camilla.estado = EstadoCamilla.Libre

        println("""
            === FICHA DE ALTA #$contadorFichas ===
            Pokemon: ${pokemon.nombrePokemon}
            Codigo: ${pokemon.idPokedex}
            Categoria: ${pokemon.tipoPokemon}
            Tiempo: $tiempoMinutos minutos
            Total: \$${"%.2f".format(total)}
            ====================
        """.trimIndent())

    }

    fun contarCamillasDisponibles(): Int {
        return camillas.count {
            it.estado is EstadoCamilla.Libre
        }
    }

    fun filtrarPokemonVIP(): List<PokemonModel> {
        return historial.filter {it.tipoEntrenador == TipoEntrenador.VIP}
    }

    fun calcularCostoPromedio(): Double {
        if (historial.isEmpty()) return 0.0
        return recaudacionTotal / historial.size
    }

    fun obtenerPokemonMasTiempo(): PokemonModel? {
        return historial.maxByOrNull { it.fechaIngreso }
    }

    fun obtenerCodigosDadosDeAlta(): List<String> {
        return historial.map { it.idPokedex }
    }

    fun obtenerCategoriaMasIngresos(): TipoPokemon? {
        return recaudacionPorCategoria.maxByOrNull { it.value }?.key
    }

    fun generarReporte() {
        println("""
        ========== REPORTE DE TURNO ==========
        Centro: $nombre
        Pokemon atendidos: ${historial.size}
        Recaudacion total: \$${"%.2f".format(recaudacionTotal)}
        Costo promedio: \$${"%.2f".format(calcularCostoPromedio())}
        Camillas disponibles: ${contarCamillasDisponibles()}
        
        --- Detalle por Pokemon ---
    """.trimIndent())

        for (ficha in fichasDeAlta) {
            println("#${ficha.numeroFicha} | ${ficha.pokemon.tipoPokemon} | ${ficha.pokemon.idPokedex} | ${ficha.tiempoMinutos} min | \$${"%.2f".format(ficha.montoPagado)}")
        }

        println("")
        println("--- Recaudacion por categoria ---")
        for ((tipo, monto) in recaudacionPorCategoria) {
            println("$tipo: \$${"%.2f".format(monto)}")
        }

        val categoriaMasIngresos = obtenerCategoriaMasIngresos()
        if (categoriaMasIngresos != null) {
            println("\nCategoria con mas ingresos: $categoriaMasIngresos")
        }

        println("")
        println("--- Pokemon VIP atendidos ---")
        val vipList = filtrarPokemonVIP()
        if (vipList.isEmpty()) {
            println("Ninguno")
        } else {
            for (p in vipList) {
                println("${p.nombrePokemon} (${p.idPokedex})")
            }
        }

        println("")
        println("--- Codigos dados de alta ---")
        val codigos = obtenerCodigosDadosDeAlta()
        if (codigos.isEmpty()) {
            println("Ninguno")
        } else {
            println(codigos.joinToString(", "))
        }

        println("=====================================")
    }
}