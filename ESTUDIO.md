# Estudio POO en Kotlin - Guía para Examen

## 1. Fundamentos de Kotlin

### 1.1 Variables

```kotlin
val inmutable = "No cambia"      // Equivale a final en Java
var mutable = "Puede cambiar"    // Variable normal
```

**Regla:** Usa `val` siempre que sea posible. Solo usa `var` si el valor necesita cambiar.

### 1.2 Tipos de Datos

| Tipo | Ejemplo | Descripción |
|------|---------|-------------|
| `Int` | `42` | Número entero |
| `Double` | `3.14` | Número decimal |
| `String` | `"Hola"` | Texto |
| `Boolean` | `true` | Verdadero/Falso |
| `Char` | `'A'` | Un carácter |
| `Long` | `100L` | Entero grande |
| `Float` | `3.14f` | Decimal preciso |

### 1.3 Type Inference

Kotlin infiere el tipo automáticamente:
```kotlin
val nombre = "Pikachu"   // Kotlin sabe que es String
val vida = 100           // Kotlin sabe que es Int
```

### 1.4 String Templates

```kotlin
val nombre = "Pikachu"
val nivel = 15

println("Mi Pokémon es $nombre")           // Variable simple
println("Nivel: ${nivel * 2}")             // Expresión
println("Precio: \$100")                   // Carácter literal $
```

---

## 2. Control de Flujo

### 2.1 If/Else

```kotlin
// Igual que Java
if (condicion) {
    // código
} else {
    // código
}

// En Kotlin, if retorna un valor (como ternario)
val resultado = if (x > 10) "Mayor" else "Menor"
```

### 2.2 When (Switch mejorado)

```kotlin
// Sin argumentos - reemplaza switch complejo
when {
    x < 0 -> println("Negativo")
    x == 0 -> println("Cero")
    else -> println("Positivo")
}

// Con argumento
when (tipo) {
    "fuego" -> println("Tipo fuego")
    "agua" -> println("Tipo agua")
    "planta" -> println("Tipo planta")
    else -> println("Tipo desconocido")
}

// Con rangos
when (nivel) {
    in 1..10 -> println("Nivel bajo")
    in 11..50 -> println("Nivel medio")
    else -> println("Nivel alto")
}
```

### 2.3 For Loops

```kotlin
// Rango básico
for (i in 1..5) { }         // 1, 2, 3, 4, 5
for (i in 1 until 5) { }    // 1, 2, 3, 4 (sin el 5)
for (i in 5 downTo 1) { }   // 5, 4, 3, 2, 1
for (i in 1..10 step 2) { } // 1, 3, 5, 7, 9

// Iterar lista
val lista = listOf("A", "B", "C")
for (item in lista) { }
for (i in lista.indices) { }  // i es el índice

// Con índice
for ((index, value) in lista.withIndex()) {
    println("$index: $value")
}
```

### 2.4 While y Do-While

```kotlin
while (condicion) { }
do { } while (condicion)
```

---

## 3. Null Safety (Seguridad contra Null)

### 3.1 Variables Nullable

```kotlin
var nombre: String = "Pikachu"    // NO puede ser null
var nullable: String? = null      // Puede ser null
```

### 3.2 Operador ?. (Safe Call)

```kotlin
var nombre: String? = null
println(nombre?.length)   // Imprime null (no lanza error)
println(nombre?.uppercase())  // null si es null
```

### 3.3 Operador ?: (Elvis)

```kotlin
var nombre: String? = null
val largo = nombre?.length ?: 0  // Si es null, usa 0
```

### 3.4 Operador !! (Not Null Assertion)

```kotlin
var nombre: String? = "Pikachu"
println(nombre!!.length)  // Fuerza que no es null (lanza error si lo es)

// ¡CUIDADO! Solo usa !! si estás 100% seguro de que no es null
```

### 3.5 Let (Bloque con null check)

```kotlin
var nombre: String? = "Pikachu"
nombre?.let {
    println("El nombre es $it")  // Solo se ejecuta si no es null
    println("Largo: ${it.length}")
}
```

---

## 4. Funciones

### 4.1 Sintaxis Básica

```kotlin
// Kotlin
fun sumar(a: Int, b: Int): Int {
    return a + b
}

// Con expresión cuerpo (una línea)
fun sumar(a: Int, b: Int) = a + b
```

**Comparación con Java:**
```java
// Java
public int sumar(int a, int b) {
    return a + b;
}
```

### 4.2 Parámetros por Defecto

```kotlin
fun saludar(nombre: String, veces: Int = 1) {
    repeat(veces) { println("Hola $nombre") }
}

saludar("Ash")           // Hola Ash (1 vez)
saludar("Ash", 3)        // Hola Ash x3
```

### 4.3 Named Arguments

```kotlin
fun crearPokemon(nombre: String, tipo: String, nivel: Int) { }

// Puedes pasar en cualquier orden usando nombres
crearPokemon(nivel = 5, nombre = "Pikachu", tipo = "electrico")
```

### 4.4 Unit (Void de Kotlin)

```kotlin
// Unit es el tipo de retorno vacío (como void en Java)
fun imprimirMensaje(mensaje: String): Unit {
    println(mensaje)
}

// Puedes omitir Unit
fun imprimirMensaje(mensaje: String) {
    println(mensaje)
}
```

### 4.5 Funciones de Extensión

```kotlin
// Agregar métodos a clases existentes
fun String.saludar() {
    println("Hola, soy $this")
}

"Juan".saludar()  // Hola, soy Juan
```

---

## 5. Clases y Objetos

### 5.1 Clase Básica

```kotlin
class Pokemon(val nombre: String, var nivel: Int) {
    fun atacar() {
        println("$nombre ataca con nivel $nivel")
    }
}

val pikachu = Pokemon("Pikachu", 15)
pikachu.atacar()
```

**Java:**
```java
public class Pokemon {
    private final String nombre;
    private int nivel;

    public Pokemon(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public void atacar() {
        System.out.println(nombre + " ataca con nivel " + nivel);
    }
}
```

### 5.2 Constructores

```kotlin
// Constructor primario (en la declaración)
class Pokemon(val nombre: String, var nivel: Int)

// Constructor secundario
class Pokemon(val nombre: String) {
    var nivel: Int = 1

    constructor(nombre: String, nivel: Int) : this(nombre) {
        this.nivel = nivel
    }
}

// Bloque init
class Pokemon(val nombre: String) {
    init {
        println("Pokémon $nombre creado")
    }
}
```

### 5.3 Propiedades

```kotlin
class Pokemon(val nombre: String) {
    var nivel: Int = 1
        get() = field       // Getter personalizado
        set(value) {         // Setter personalizado
            if (value > 0) field = value
        }
}

// Propiedades calculadas
class Pokemon(val nombre: String, val vida: Int) {
    val estaVivo: Boolean
        get() = vida > 0
}
```

### 5.4 Data Classes

Para clases que solo contienen datos:

```kotlin
data class Pokemon(val nombre: String, val tipo: String, val nivel: Int)

// Genera automáticamente:
// equals() - comparar objetos
// hashCode() - para usar en HashSet
// toString() - representación en string
// copy() - copiar objeto
// componentN() - destructuring

val pikachu = Pokemon("Pikachu", "electrico", 15)
val copia = pikachu.copy(nivel = 20)  // Copia con nivel 20
val (nombre, tipo, nivel) = pikachu   // Destructuring
```

### 5.5 Sealed Classes

Para jerarquías de clases restringidas donde cada variante puede tener **datos diferentes**:

```kotlin
sealed class Resultado
class Exito(val mensaje: String) : Resultado()
class Error(val codigo: Int) : Resultado()

fun procesar(resultado: Resultado) {
    when (resultado) {
        is Exito -> println(resultado.mensaje)
        is Error -> println("Error ${resultado.codigo}")
        // No necesita else porque sealed cubre todos los casos
    }
}
```

**¿Cuándo usar sealed class vs enum?**

| Característica | Enum | Sealed Class |
|----------------|------|--------------|
| Datos diferentes por variante | No | Sí |
| Solo valores fijos | Sí | No |
| Cada variante tiene propiedades distintas | No | Sí |
| Ejemplo simple | TipoPokemon | EstadoCamilla |

**Ejemplo práctico - EstadoCamilla:**
```kotlin
sealed class EstadoCamilla {
    object Libre : EstadoCamilla()
    data class Ocupada(val pokemon: PokemonModel) : EstadoCamilla()
    data class EnProceso(val motivo: String) : EstadoCamilla()
    data class FueraDeServicio(val motivo: String) : EstadoCamilla()
}

// Uso con when
fun descripcion(estado: EstadoCamilla): String {
    return when (estado) {
        is EstadoCamilla.Libre -> "Disponible"
        is EstadoCamilla.Ocupada -> "Ocupada por ${estado.pokemon.nombrePokemon}"
        is EstadoCamilla.EnProceso -> estado.motivo
        is EstadoCamilla.FueraDeServicio -> "Fuera de servicio: ${estado.motivo}"
    }
}
```

**Ventajas de sealed class:**
- Kotlin te obliga a cubrir todos los casos en `when`
- Cada variante solo lleva los datos que necesita
- Es type-safe: no puedes tener un estado inválido

---

## 6. Herencia

### 6.1 Clases Open

En Kotlin, las clases son `final` por defecto (no se pueden heredar). Usa `open`:

```kotlin
open class Pokemon(val nombre: String) {
    open fun atacar() {     // open = puede ser sobreescrito
        println("$nombre ataca")
    }
}

class PokemonElectrico(nombre: String) : Pokemon(nombre) {
    override fun atacar() {  // override = sobreescribe el método
        println("$nombre lanza un rayo")
    }
}
```

**Java:**
```java
// Java - todas las clases son heredables por defecto
public class Pokemon {
    public void atacar() { }
}

public class PokemonElectrico extends Pokemon {
    @Override
    public void atacar() { }
}
```

### 6.2 Clases Abstract

No se pueden instanciar, solo heredar:

```kotlin
abstract class Pokemon(val nombre: String) {
    abstract fun calcularCosto(): Double  // Sin implementación
}

class PokemonElectrico(nombre: String) : Pokemon(nombre) {
    override fun calcularCosto(): Double = 1500.0  // Debe implementarse
}
```

### 6.3 Interfaces

```kotlin
interface Sanable {
    fun sanar()  // Método abstracto
    fun descansar() {   // Método con implementación por defecto
        println("Descansando...")
    }
}

class Pokemon(nombre: String) : Sanable {
    override fun sanar() {
        println("Sanando...")
    }
    // descansar() ya tiene implementación
}
```

### 6.4 Jerarquía de Herencia

```
         Any (todas las clases heredan de aquí)
           │
      ┌────┴────┐
   Pokemon    Camilla
      │
   ┌──┴──┐
   │     │
Electrico Agua Dragon
```

---

## 7. Polimorfismo

### 7.1 Polymorphism

```kotlin
open class Pokemon(val nombre: String) {
    open fun hacerSonido() {
        println("...")
    }
}

class Pikachu(nombre: String) : Pokemon(nombre) {
    override fun hacerSonido() {
        println("Pika pika!")
    }
}

class Charmander(nombre: String) : Pokemon(nombre) {
    override fun hacerSonido() {
        println("Charmander!")
    }
}

// Polimorfismo
val pokemon: Pokemon = Pikachu("Pikachu")
pokemon.hacerSonido()  // Pika pika! (se ejecuta el método de Pikachu)
```

### 7.2 Tipos de Polimorfismo

**1. Polimorfismo de sobrecarga (Overloading)**
```kotlin
fun atacar(pokemon: Pokemon) { }
fun atacar(pokemon: Pokemon, ataque: String) { }
```

**2. Polimorfismo de sobreescribir (Overriding)**
```kotlin
open class Pokemon {
    open fun atacar() { }
}
class Pikachu : Pokemon() {
    override fun atacar() { }
}
```

---

## 8. Enum Classes

```kotlin
enum class TipoPokemon {
    ELECTRICO,
    AGUA,
    DRAGON
}

// Con propiedades
enum class Estado(val descripcion: String) {
    LIBRE("Disponible"),
    OCUPADA("Ocupada"),
    EN_PROCESO("Procesando")
}

// Con métodos
enum class Tipo(val multiplicador: Double) {
    FUEGO(1.5) {
        override fun ventaja() = "Planta"
    },
    AGUA(1.2) {
        override fun ventaja() = "Fuego"
    };

    abstract fun ventaja(): String
}

// Uso
val tipo = TipoPokemon.ELECTRICO
println(Tipo.FUEGO.multiplicador)  // 1.5
println(Tisto.FUEGO.ventaja())     // Planta
```

---

## 9. Collections (Colecciones)

### 9.1 List (Inmutable)

```kotlin
val lista = listOf("A", "B", "C")
println(lista[0])           // Acceder por índice
println(lista.size)         // Tamaño
println(lista.contains("A")) // Verificar si existe

// No se puede agregar/quitar elementos
// lista.add("D")  // ERROR
```

### 9.2 MutableList (Mutable)

```kotlin
val lista = mutableListOf<String>()
lista.add("A")
lista.remove("A")
lista.addAll(listOf("B", "C"))
```

### 9.3 Map (Diccionario)

```kotlin
// Map inmutable
val mapa = mapOf("nombre" to "Pikacidad", "nivel" to 15)
println(mapa["nombre"])  // Pikachu
println(mapa["ataque"])  // null (no existe)

// Mutable Map (se puede agregar/quitar elementos)
val mapaMutable = mutableMapOf<String, Int>()
mapaMutable["ataque"] = 50
mapaMutable["defensa"] = 30
mapaMutable["velocidad"] = 80

// Acceder a valores
println(mapaMutable["ataque"])  // 50

// Actualizar valor
mapaMutable["ataque"] = 60

// Eliminar elemento
mapaMutable.remove("defensa")

// Verificar si existe una clave
println(mapaMutable.containsKey("ataque"))   // true
println(mapaMutable.containsValue(80))       // true

// Obtener valor con fallback (si no existe, usa el valor por defecto)
val valor = mapaMutable.getOrDefault("magia", 0)  // 0

// Iterar sobre un map
for ((clave, valor) in mapaMutable) {
    println("$clave: $valor")
}
```

**Comparación con Java:**
```java
// Java
Map<String, Integer> mapa = new HashMap<>();
mapa.put("ataque", 50);
mapa.get("ataque");           // 50
mapa.containsKey("ataque");  // true
mapa.remove("ataque");
```

### 9.4 Set (Sin duplicados)

```kotlin
val set = setOf("A", "B", "A")  // {A, B}
```

### 9.5 Operaciones Funcionales

```kotlin
val numeros = listOf(1, 2, 3, 4, 5)

// filter - filtrar elementos
val pares = numeros.filter { it % 2 == 0 }  // [2, 4]

// map - transformar elementos
val dobles = numeros.map { it * 2 }  // [2, 4, 6, 8, 10]

// find - encontrar primer elemento
val primero = numeros.find { it > 3 }  // 4

// any / all / none
numeros.any { it > 3 }    // true
numeros.all { it > 0 }    // true
numeros.none { it > 10 }  // true

// count
numeros.count { it > 2 }  // 3

// reduce / fold
val suma = numeros.reduce { acc, i -> acc + i }  // 15

// forEach - ejecutar una acción para cada elemento
numeros.forEach { println(it) }

// forEach con índice
numeros.forEachIndexed { indice, valor ->
    println("$indice: $valor")
}

// forEach en un map
val pokemon = mapOf("Pikachu" to 15, "Charmander" to 10)
pokemon.forEach { (nombre, nivel) ->
    println("$nombre tiene nivel $nivel")
}

// forEach en una lista de objetos
data class Entrenador(val nombre: String, val cantidadPokemon: Int)
val entrenadores = listOf(
    Entrenador("Ash", 6),
    Entrenador("Misty", 3),
    Entrenador("Brock", 2)
)

entrenadores.forEach { entrenador ->
    println("${entrenador.nombre} tiene ${entrenador.cantidadPokemon} Pokémon")
}

// sorted
numeros.sorted()           // [1, 2, 3, 4, 5]
numeros.sortedDescending() // [5, 4, 3, 2, 1]

// groupBy
val personas = listOf("Ana", "Juan", "Ana", "Pedro")
personas.groupBy { it.first() }  // {A=[Ana, Ana], J=[Juan], P=[Pedro]}

// joinToString - convertir lista a string
val codigos = listOf("PK0001", "PK0002", "PK0003")
println(codigos.joinToString(", "))  // "PK0001, PK0002, PK0003"
println(codigos.joinToString(" | "))  // "PK0001 | PK0002 | PK0003"
```

### 9.6 Validaciones con If y Colecciones

Kotlin ofrece métodos útiles para validar datos antes de usarlos:

```kotlin
// isEmpty() / isNotEmpty() - verificar si una colección está vacía
val lista = listOf<String>()

if (lista.isEmpty()) {
    println("La lista está vacía")
}

if (lista.isNotEmpty()) {
    println("La lista tiene elementos")
}

// isNullOrEmpty() - para strings y colecciones nullable
val nombre: String? = null
if (nombre.isNullOrEmpty()) {
    println("El nombre es null o está vacío")
}

// isNullOrBlank() - como isNullOrEmpty pero también verifica espacios en blanco
val texto = "   "
if (texto.isNullOrBlank()) {
    println("El texto es null, vacío o solo tiene espacios")
}

// Verificar tamaño de colección
val pokemon = listOf("Pikachu", "Charmander", "Squirtle")
if (pokemon.size >= 3) {
    println("Hay al menos 3 Pokémon")
}

// firstOrNull() / lastOrNull() - obtener elemento o null si está vacío
val primerPokemon = pokemon.firstOrNull()  // "Pikachu"
val pokemonVacio = emptyList<String>().firstOrNull()  // null

// any() / none() - verificar si algún elemento cumple condición
val numeros = listOf(1, 2, 3, 4, 5)
if (numeros.any { it > 3 }) {
    println("Hay números mayores a 3")
}

if (numeros.none { it > 10 }) {
    println("No hay números mayores a 10")
}

// count() - contar elementos que cumplen condición
val pares = numeros.count { it % 2 == 0 }  // 2

// find() - encontrar primer elemento que cumple condición
val primerPar = numeros.find { it % 2 == 0 }  // 2
```

**Ejemplo práctico en el proyecto:**
```kotlin
// En CentroPokemon
fun contarCamillasDisponibles(): Int {
    return camillas.count { it.estado is EstadoCamilla.Libre }
}

fun filtrarPokemonVIP(): List<PokemonModel> {
    return historial.filter { it.tipoEntrenador == TipoEntrenador.VIP }
}

// Uso en Main.kt
val centro = CentroPokemon()
if (centro.contarCamillasDisponibles() > 0) {
    println("Hay camillas disponibles")
} else {
    println("El centro está lleno")
}

if (centro.historial.isNotEmpty()) {
    println("Se atendieron ${centro.historial.size} Pokémon")
}
```

---

## 10. Coroutines (Corrutinas)

### 10.1 ¿Qué problema resuelven?

Imagina que necesitas esperar 3 segundos antes de continuar (por ejemplo, mientras un Pokémon se ingresa al centro). Sin corrutinas:

```kotlin
fun main() {
    println("Ingresando Pokémon...")
    Thread.sleep(3000)  // TODO el programa se CONGELA durante 3 segundos
    println("Pokémon ingresado")
    // El usuario no puede hacer nada mientras tanto
}
```

Con corrutinas:

```kotlin
fun main() = runBlocking {
    println("Ingresando Pokémon...")
    delay(3000)  // Pausa 3 segundos, pero el hilo LIBRE para otro código
    println("Pokémon ingresado")
}
```

**¿Cómo funciona internamente?** Kotlin "pausa" la función y libera el hilo. Cuando pasan los 3 segundos, Kotlin reanuda la función exactamente donde la dejó. Es como poner una película en pausa y reanudarla después.

### 10.2 Función `suspend`

Una función `suspend` es una función que **puede pausarse**. Solo puede llamar a otras funciones suspend o estar dentro de un scope de corrutinas.

```kotlin
import kotlinx.coroutines.delay

suspend fun ingresarPokemon(nombre: String) {
    println("Ingresando $nombre...")
    delay(3000)  // ✅ Puede usar delay porque es suspend
    println("$nombre ingresado")
}

// ❌ Esto NO compila:
fun noFunciona() {
    delay(1000)  // ERROR: delay solo funciona en suspend
}
```

**Regla:** Si una función usa `delay`, `launch`, `async` o llama a otra `suspend fun`, DEBE ser `suspend`.

### 10.3 `runBlocking`

`runBlocking` crea un **scope de corrutinas** que bloquea el hilo principal hasta que todas las corrutinas dentro terminen. Solo se usa en:
- `fun main()`
- Tests

```kotlin
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.delay

fun main() = runBlocking {
    println("Inicio")
    delay(1000)
    println("Fin")
}
// Salida: Inicio → (espera 1 seg) → Fin
```

**¿Por qué no usarlo siempre?** Porque bloquea el hilo. En una app real (Android), si usas `runBlocking` en el hilo principal, la app se congela. Se usa solo para arrancar el programa.

### 10.4 `launch` (Ejecutar en paralelo)

`launch` lanza una corrutina que ejecuta código **en paralelo** sin esperar que termine:

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {  // Tarea 1 - se ejecuta en paralelo
        delay(1000)
        println("Tarea 1")
    }

    launch {  // Tarea 2 - se ejecuta en paralelo
        delay(500)
        println("Tarea 2")
    }

    println("Inicio")
}
// Salida: Inicio → Tarea 2 (500ms) → Tarea 1 (1000ms)
```

**Nota:** "Inicio" se imprime primero porque las tareas son asíncronas.

### 10.5 `async` (Obtener resultado)

`async` es como `launch` pero **retorna un resultado**:

```kotlin
fun main() = runBlocking {
    val resultado = async {
        delay(1000)
        42  // Este es el valor que retorna
    }
    println("Resultado: ${resultado.await()}")  // await() espera y obtiene el valor
}
// Salida: Resultado: 42
```

### 10.6 Ejemplo del Proyecto: CentroPokemon

En nuestro proyecto, las funciones de ingresso y alta usan `suspend` con `delay`:

```kotlin
class CentroPokemon {
    // ...

    suspend fun ingresarPokemon(pokemon: PokemonModel) {
        val camilla = buscarCamillaLibre()
        if (camilla == null) {
            println("Error: centro sin capacidad")
            return
        }

        // Estado "En Proceso" durante la espera
        camilla.estado = EstadoCamilla.EnProceso(motivo = "Ingresando Pokémon...")
        println("Camilla ${camilla.numero} - Ingresando ${pokemon.nombrePokemon}...")

        delay(3000)  // 3 segundos SIN bloquear el programa

        camilla.estado = EstadoCamilla.Ocupada(pokemon = pokemon)
        println("${pokemon.nombrePokemon} ingresado en camilla ${camilla.numero}")
    }

    suspend fun darDeAlta(codigo: String) {
        // ... validaciones ...

        camilla.estado = EstadoCamilla.EnProceso(motivo = "Procesando alta y cobro...")
        delay(6500)  // 6.5 segundos

        // ... cálculo de costos ...
        camilla.estado = EstadoCamilla.Libre
    }
}

// Main.kt
fun main() = runBlocking {
    val centro = CentroPokemon()
    centro.ingresarPokemon(pikachu)  // Puede llamar suspend porque está en runBlocking
    centro.darDeAlta("PK1001")
    centro.generarReporte()
}
```

### 10.7 Coroutines vs Thread

```kotlin
// ❌ Thread - bloquea y pesado
fun main() {
    println("Inicio")
    Thread.sleep(3000)  // Bloquea todo el hilo
    println("Fin")
}

// ✅ Coroutine - no bloquea y ligero
fun main() = runBlocking {
    println("Inicio")
    delay(3000)  // Pausa sin bloquear
    println("Fin")
}
```

| Thread | Coroutine |
|--------|-----------|
| `Thread.sleep(1000)` bloquea el hilo | `delay(1000)` pausa sin bloquear |
| Pesado en memoria (1 thread = 1MB) | Ligero en memoria (1 coroutine = ~nada) |
| Difícil de controlar | Fácil de cancelar |
| Pool de threads limitado | Miles de corrutinas posibles |
| Bloquea el hilo principal de Android | No bloquea la UI de Android |

---

## 11. Collections Avanzado

### 11.1 Crear Colecciones

```kotlin
// Listas
val lista1 = listOf(1, 2, 3)           // Inmutable
val lista2 = mutableListOfOf(1, 2, 3)  // Mutable
val lista3 = emptyList<Int>()           // Vacía

// Maps
val mapa1 = mapOf("a" to 1, "b" to 2)
val mapa2 = mutableMapOf<String, Int>()

// Sets
val set1 = setOf(1, 2, 3)
val set2 = mutableSetOf<Int>()
```

### 11.2 Operaciones Encadenadas

```kotlin
val resultado = numeros
    .filter { it % 2 == 0 }    // [2, 4]
    .map { it * 10 }            // [20, 40]
    .sorted()                    // [20, 40]
    .take(1)                     // [20]
```

### 11.3 Crear Colecciones

```kotlin
val rango = 1..10           // Rango cerrado
val rango2 = 1 until 10     // Rango abierto (sin el 10)

(1..10).toList()             // Convierte a lista
(1..10).step(2).toList()    // [1, 3, 5, 7, 9]
```

---

## 12. Expresiones Lambda

### 12.1 Sintaxis

```kotlin
// Lambda básica
val sumar = { a: Int, b: Int -> a + b }
println(sumar(2, 3))  // 5

// Con un solo parámetro (it)
val doblar = { it: Int -> it * 2 }
val doblar2: (Int) -> Int = { it * 2 }

// Si no tiene parámetros
val saludar = { println("Hola") }
```

### 12.2 Lambdas con Colecciones

```kotlin
val numeros = listOf(1, 2, 3, 4, 5)

numeros.filter { it > 3 }        // Lambda con it
numeros.map { it * it }          // Cuadrado de cada número
numeros.forEach { println(it) }  // Imprimir cada uno
```

### 12.3 Funciones como Parámetro

```kotlin
fun ejecutar.operacion(a: Int, b: Int, operacion: (Int, Int) -> Int): Int {
    return operacion(a, b)
}

val suma = ejecutar(2, 3) { a, b -> a + b }
val producto = ejecutar(2, 3) { a, b -> a * b }
```

---

## 13. Type Casting (Conversión de Tipos)

### 13.1 is (Instaceof)

```kotlin
fun describir(objeto: Any) {
    when (objeto) {
        is String -> println("String: $objeto")      // Smart cast
        is Int -> println("Int: $objeto")
        is Pokemon -> println("Pokemon: ${objeto.nombre}")
    }
}
```

### 13.2 as (Conversión explícita)

```kotlin
val pokemon: Pokemon = obtenerPokemon()
val electrico = pokemon as PokemonElectrico  // Unsafe cast
val electrico2 = pokemon as? PokemonElectrico  // Safe cast (nullable)
```

---

## 14. Extension Functions

Agregar funcionalidad a clases existentes sin heredar:

```kotlin
// Agregar método a String
fun String.esMayuscula(): Boolean {
    return this == this.uppercase()
}

"HELLO".esMayuscula()  // true

// Agregar propiedad
val String.primerCaracter: Char
    get() = this[0]

"Hola".primerCaracter  // 'H'
```

---

## 15. Scope Functions

### 15.1 let

```kotlin
val nombre: String? = "Pikachu"
nombre?.let {
    println("El nombre es ${it.uppercase()}")
}
```

### 15.2 apply

Configurar un objeto después de crearlo:
```kotlin
val pokemon = Pokemon().apply {
    nombre = "Pikachu"
    nivel = 15
    tipo = "electrico"
}
```

### 15.3 also

Ejecutar un efecto secundario:
```kotlin
val numeros = mutableListOf(1, 2, 3).also {
    println("Lista original: $it")
}
```

### 15.4 with

Trabajar con un objeto existente:
```kotlin
with(pokemon) {
    println(nombre)
    println(nivel)
    atacar()
}
```

### 15.5 run

Ejecutar un bloque con un objeto:
```kotlin
val resultado = pokemon.run {
    "Nombre: $nombre, Nivel: $nivel"
}
```

---

## 16. Operator Overloading

### 16.1 ¿Qué es?

Kotlin permite que clases propias usen operadores nativos como `+`, `-`, `*`, etc. Esto hace que el código sea más legible y natural.

**¿Por qué existe?** Sin operator overloading, para sumar dos objetos necesitarías escribir algo como `obj1.sumar(obj2)`. Con operator overloading puedes escribir `obj1 + obj2`, que es mucho más claro.

### 16.2 Sintaxis Básica

```kotlin
data class Tiempo(val minutos: Int) {
    operator fun plus(otro: Tiempo): Tiempo {
        return Tiempo(this.minutos + otro.minutos)
    }
}

val t1 = Tiempo(75)
val t2 = Tiempo(45)
val total = t1 + t2  // Tiempo(120)
println(total.minutos)  // 120
```

### 16.3 Tabla Completa de Operadores

| Operador | Función | Ejemplo | Descripción |
|----------|---------|---------|-------------|
| `+` | `plus` | `a + b` | Suma |
| `-` | `minus` | `a - b` | Resta |
| `*` | `times` | `a * b` | Multiplicación |
| `/` | `div` | `a / b` | División |
| `%` | `rem` | `a % b` | Residuo |
| `==` | `equals` | `a == b` | Igualdad |
| `>` | `compareTo` | `a > b` | Comparación |
| `>=` | `compareTo` | `a >= b` | Mayor o igual |
| `<` | `compareTo` | `a < b` | Menor |
| `<=` | `compareTo` | `a <= b` | Menor o igual |
| `+=` | `plusAssign` | `a += b` | Suma y asigna |
| `-=` | `minusAssign` | `a -= b` | Resta y asigna |
| `*=` | `timesAssign` | `a *= b` | Multiplica y asigna |
| `[]` | `get` / `set` | `a[i]` | Acceso por índice |
| `()` | `invoke` | `a()` | Invocar como función |

### 16.4 Ejemplo Práctico: CostoAcumulado

```kotlin
data class CostoAcumulado(var monto: Double) {
    operator fun plus(otro: CostoAcumulado): CostoAcumulado {
        return CostoAcumulado(this.monto + otro.monto)
    }

    operator fun times(porcentaje: Double): CostoAcumulado {
        return CostoAcumulado(this.monto * porcentaje)
    }

    override fun toString(): String = "$${"%.2f".format(monto)}"
}

val costo1 = CostoAcumulado(1500.0)
val costo2 = CostoAcumulado(2500.0)
val total = costo1 + costo2          // CostoAcumulado(4000.0)
val conDescuento = total * 0.80      // CostoAcumulado(3200.0)
println(total)          // $4000.00
println(conDescuento)   // $3200.00
```

### 16.5 Ejemplo con compareTo

```kotlin
data class Pokemon(val nombre: String, val nivel: Int) : Comparable<Pokemon> {
    override fun compareTo(otro: Pokemon): Int {
        return this.nivel - otro.nivel  // Negativo si es menor, positivo si es mayor
    }
}

val pikachu = Pokemon("Pikachu", 15)
val raichu = Pokemon("Raichu", 25)

println(pikachu > raichu)   // false (15 no es mayor que 25)
println(pikachu < raichu)   // true  (15 es menor que 25)
println(pikachu >= pikachu) // true  (15 == 15)
```

### 16.6 Cuándo Usar y Cuándo NO Usar

**Usa operator overloading cuando:**
- La operación es intuitiva (sumar costos, comparar niveles)
- Mejora la legibilidad del código
- La semántica del operador es clara

**NO uses operator overloading cuando:**
- La operación no es intuitiva (¿qué significaría `pokemon * pokemon`?)
- Confunde al lector
- Podría causar bugs difíciles de detectar

### 16.7 Comparación con Java

Java **no permite** operator overloading (excepto para `+` con Strings). En Java necesitas crear métodos explícitos:

```java
// Java - no se puede sobrecargar operadores
public class Tiempo {
    private int minutos;

    public Tiempo sumar(Tiempo otro) {
        return new Tiempo(this.minutos + otro.minutos);
    }
}

// Uso
Tiempo t1 = new Tiempo(75);
Tiempo t2 = new Tiempo(45);
Tiempo total = t1.sumar(t2);  // En vez de t1 + t2
```

---

## 17. Generics

### 17.1 ¿Qué son y por qué existen?

Los generics permiten escribir una **única función o clase** que funcione con **cualquier tipo** de dato. Sin generics, necesitarías crear una versión separada para cada tipo.

**Problema sin generics:**
```kotlin
class ContenedorString(var item: String)
class ContenedorInt(var item: Int)
class ContenedorPokemon(var item: Pokemon)
// ... necesitarías una clase para cada tipo
```

**Solución con generics:**
```kotlin
class Contenedor<T>(var item: T)  // Una sola clase para todos los tipos
```

### 17.2 Clases Genéricas

```kotlin
class Contenedor<T>(var item: T)

val cajaString = Contenedor("Hola")      // Contenedor<String>
val cajaInt = Contenedor(42)             // Contenedor<Int>
val cajaPokemon = Contenedor(Pokemon("Pikachu", 15))  // Contenedor<Pokemon>

// Acceder al item
println(cajaString.item)  // "Hola"
println(cajaInt.item)     // 42
```

### 17.3 Generics en el Proyecto PokeCare

Las colecciones que usas en el proyecto **ya son genéricas**:

```kotlin
// MutableList<CamillaModel> - lista que solo acepta CamillaModel
val camillas = mutableListOf<CamillaModel>()

// MutableList<PokemonModel> - lista que solo acepta PokemonModel
val historial = mutableListOf<PokemonModel>()

// MutableList<FichaAlta> - lista que solo acepta FichaAlta
val fichasDeAlta = mutableListOf<FichaAlta>()

// Map<TipoPokemon, Double> - mapa con claves TipoPokemon y valores Double
val recaudacionPorCategoria = mutableMapOf<TipoPokemon, Double>()
```

Cuando escribes `mutableListOf<CamillaModel>()`, estás diciendo: "esta lista solo puede guardar objetos de tipo CamillaModel". Si intentas agregar un Pokémon, Kotlin da error en tiempo de compilación.

### 17.4 Funciones Genéricas

```kotlin
fun <T> imprimir(item: T) {
    println(item)
}

imprimir("Hola")    // T = String
imprimir(42)        // T = Int
imprimir(Pokemon("Pikachu", 15))  // T = Pokemon
```

### 17.5 Restricciones de Tipo (Bounded Types)

Puedes limitar qué tipos puede usar T:

```kotlin
// T debe implementar Comparable (para poder comparar)
fun <T : Comparable<T>> minimo(a: T, b: T): T {
    return if (a < b) a else b
}

// Funciona con Int, String, Double (todos implementan Comparable)
println(minimo(5, 10))       // 5
println(minimo("abc", "xyz"))  // "abc"

// NO funcionaría con Pokemon (no implementa Comparable)
// minimo(pokemon1, pokemon2)  // ERROR
```

### 17.6 Múltiples Restricciones

```kotlin
// T debe ser Number Y Comparable
fun <T> maximo(lista: List<T>): T where T : Number, T : Comparable<T> {
    return lista.max()
}
```

### 17.7 Tipo Reified (para inline functions)

```kotlin
inline fun <reified T> List<Any>.filtrarTipo(): List<T> {
    return filterIsInstance<T>()
}

val mixta: List<Any> = listOf("Hola", 42, Pokemon("Pikachu", 15), "Mundo")
val soloPokemon = mixta.filtrarTipo<Pokemon>()  // [Pokemon("Pikachu", 15)]
val soloStrings = mixta.filtrarTipo<String>()    // ["Hola", "Mundo"]
```

### 17.8 Comparación con Java

```java
// Java - generics son similares pero menos seguros (type erasure)
List<CamillaModel> camillas = new ArrayList<>();
camillas.add(new CamillaModel(1));
// En tiempo de ejecución, Java "olvida" que es List<CamillaModel>

// Kotlin es más estricto en compilación
```

| Característica | Java | Kotlin |
|----------------|------|--------|
| Sintaxis | `List<String>` | `List<String>` |
| Type erasure | Sí (olvida tipo en runtime) | Sí, pero más seguro en compilación |
| Wildcards | `List<?>`, `List<? extends T>` | No necesarios (usa project/use site variance) |
| Real type | No disponible | `reified` con `inline fun` |

---

## 18. Try-Catch

```kotlin
// Igual que Java
try {
    val resultado = 10 / 0
} catch (e: ArithmeticException) {
    println("Error: ${e.message}")
} finally {
    println("Siempre se ejecuta")
}

// Try como expresión
val resultado = try {
    10 / 0
} catch (e: Exception) {
    0
}
```

---

## 19. Imports y Paquetes

```kotlin
// Importar una clase
import cl.ejercicio.model.Pokemon

// Importar todo de un paquete
import cl.ejercicio.model.*

// Importar con alias
import cl.ejercicio.model.Pokemon as Pkm
```

---

## 20. Fechas y Horas (LocalDateTime y ChronoUnit)

### 20.1 LocalDateTime

Para manejar fechas y horas:

```kotlin
import java.time.LocalDateTime

// Obtener fecha y hora actual
val ahora = LocalDateTime.now()
println(ahora)  // 2024-01-15T14:30:45.123

// Crear fecha específica
val fechaEspecifica = LocalDateTime.of(2024, 1, 15, 14, 30, 0)

// Acceder a componentes
println(ahora.year)       // 2024
println(ahora.monthValue) // 1
println(ahora.dayOfMonth) // 15
println(ahora.hour)       // 14
println(ahora.minute)     // 30
println(ahora.second)     // 45
```

### 20.2 ChronoUnit (Calcular Diferencias)

Para calcular la diferencia entre dos fechas:

```kotlin
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

val fechaInicio = LocalDateTime.of(2024, 1, 15, 8, 0, 0)
val fechaFin = LocalDateTime.of(2024, 1, 15, 14, 30, 0)

// Calcular diferencia en diferentes unidades
val minutos = ChronoUnit.MINUTES.between(fechaInicio, fechaFin)  // 390
val horas = ChronoUnit.HOURS.between(fechaInicio, fechaFin)      // 6
val dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin)        // 0

// Ejemplo práctico: calcular tiempo de tratamiento
val fechaIngreso = LocalDateTime.now()
// ... después de un tiempo ...
val tiempoMinutos = ChronoUnit.MINUTES.between(fechaIngreso, LocalDateTime.now()).toInt()
```

### 20.3 Ejemplo en el Proyecto

```kotlin
// En CentroPokemon.kt
suspend fun darDeAlta(codigo: String) {
    // ...
    
    // Calcular tiempo de tratamiento automáticamente
    val tiempoMinutos = ChronoUnit.MINUTES.between(
        pokemon.fechaIngreso,  // Cuando ingresó
        LocalDateTime.now()    // Ahora
    ).toInt()
    
    // Usar el tiempo para calcular el costo
    val costoBase = pokemon.calcularCosto(tiempoMinutos)
    // ...
}
```

**¿Por qué usar ChronoUnit en vez de pasar el tiempo manualmente?**
- Calcula el tiempo real de tratamiento
- No depende de un valor que el usuario pueda ingresar incorrectamente
- Es más seguro y preciso

**Comparación con Java:**
```java
// Java
long minutos = ChronoUnit.MINUTES.between(fechaInicio, fechaFin);
```

---

## 21. Convenciones de Código

| Regla | Ejemplo |
|-------|---------|
| Variables y funciones | camelCase (`nombrePokemon`) |
| Clases y Enums | PascalCase (`PokemonElectrico`) |
| Constantes | SCREAMING_SNAKE_CASE (`MAX_VIDA`) |
| Paquetes | lowercase (`cl.ejercicio.model`) |
| Funciones | verbos (`calcularCosto()`, `obtenerPokemon()`) |
| Booleanos | prefijo `es`, `tiene`, `puede` (`esVivo`) |

---

## 22. Comparación Final Kotlin vs Java

| Concepto | Java | Kotlin |
|----------|------|--------|
| Variable final | `final String x = "h"` | `val x = "h"` |
| Variable mutable | `String x = "h"` | `var x = "h"` |
| Null check | `if (x != null)` | `x?.let { }` |
| Crear objeto | `new Pokemon()` | `Pokemon()` |
| Herencia | `extends` | `:` |
| Override | `@Override` | `override` |
| Abstract class | `abstract class` | `abstract class` |
| Interface | `interface` | `interface` |
| Switch | `switch` | `when` |
| Coroutines | `Thread.sleep()` | `delay()` |
| String concat | `"h" + nombre` | `"h $nombre"` |
| Getter/Setter | `getX()` / `setX()` | `obj.x` |
| Void | `void` | `Unit` |
| Static | `static` | `companion object` |
| Stream | `stream().filter()` | `filter { }` |

---

## 23. Ejemplos del Proyecto PokeCare

Esta sección conecta los conceptos teóricos con el código real del proyecto.

### 23.1 Enums (Sección 8)

**Concepto:** Valores fijos que representan categorías.

```kotlin
// model/TipoPokemon.kt
enum class TipoPokemon {
    ELECTRICO,
    AGUA,
    DRAGON
}

// model/TipoEntrenador.kt
enum class TipoEntrenador {
    NOVATO,
    VIP,
    LEGENDARIO
}

// Uso en CentroPokemon.kt
val tipo = TipoPokemon.ELECTRICO  // Acceso con punto
when (pokemon.tipoPokemon) {
    TipoPokemon.ELECTRICO -> println("Eléctrico")
    TipoPokemon.AGUA -> println("Agua")
    TipoPokemon.DRAGON -> println("Dragón")
}
```

### 23.2 Sealed Classes (Sección 5.5)

**Concepto:** Jerarquía restringida donde cada variante tiene datos diferentes.

```kotlin
// model/EstadoCamilla.kt
sealed class EstadoCamilla {
    object Libre : EstadoCamilla()                              // Sin datos
    data class Ocupada(val pokemon: PokemonModel) : EstadoCamilla()  // Tiene pokemon
    data class EnProceso(val motivo: String) : EstadoCamilla()       // Tiene motivo
    data class FueraDeServicio(val motivo: String) : EstadoCamilla() // Tiene motivo
}

// Uso con when (obligatorio cubrir todos los casos)
when (val estado = camilla.estado) {
    is EstadoCamilla.Libre -> println("Disponible")
    is EstadoCamilla.Ocupada -> println("Ocupada por ${estado.pokemon.nombrePokemon}")
    is EstadoCamilla.EnProceso -> println(estado.motivo)
    is EstadoCamilla.FueraDeServicio -> println("Fuera: ${estado.motivo}")
}
```

### 23.3 Herencia y Polimorfismo (Secciones 6 y 7)

**Concepto:** Clase base con comportamiento común, subclases con comportamiento específico.

```kotlin
// model/PokemonModel.kt - Clase base (open = puede heredarse)
open class PokemonModel(
    val idPokedex: String,
    val nombrePokemon: String,
    val tipoPokemon: TipoPokemon,
    val tipoEntrenador: TipoEntrenador,
    val fechaIngreso: LocalDateTime
) {
    open fun calcularCosto(tiempoMinutos: Int): Double {
        return 0.0  // Implementación por defecto
    }
}

// model/PokemonElectrico.kt - Hereda y sobreescribe
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

// Polimorfismo: mismo método, diferente comportamiento
val pikachu: PokemonModel = PokemonElectrico(...)
val costo = pikachu.calcularCosto(75)  // Usa calcularCosto de PokemonElectrico
```

### 23.4 Data Classes (Sección 5.4)

**Concepto:** Clases que solo contienen datos. Kotlin genera automáticamente equals, hashCode, toString, copy.

```kotlin
// model/FichaAlta.kt
data class FichaAlta(
    val numeroFicha: Int,
    val pokemon: PokemonModel,
    val tiempoMinutos: Int,
    val montoPagado: Double
)

// Uso
val ficha = FichaAlta(1, pikachu, 75, 1785.00)
println(ficha)  // FichaAlta(numeroFicha=1, pokemon=..., tiempoMinutos=75, montoPagado=1785.0)

// Copia con cambios
val ficha2 = ficha.copy(montoPagado = 2000.00)
```

### 23.5 Colecciones y Operaciones Funcionales (Secciones 9 y 11)

```kotlin
// En CentroPokemon.kt
val camillas = mutableListOf<CamillaModel>()  // Lista mutable
val historial = mutableListOf<PokemonModel>()
val recaudacionPorCategoria = mutableMapOf<TipoPokemon, Double>()

// filter - filtrar elementos
val vip = historial.filter { it.tipoEntrenador == TipoEntrenador.VIP }

// count - contar elementos
val disponibles = camillas.count { it.estado is EstadoCamilla.Libre }

// map - transformar elementos
val codigos = historial.map { it.idPokedex }

// find - encontrar primer elemento
val dragonite = historial.find { it.nombrePokemon == "Dragonite" }

// maxByOrNull - encontrar el mayor
val masTiempo = historial.maxByOrNull { it.fechaIngreso }

// forEach - ejecutar acción para cada elemento
fichasDeAlta.forEach { ficha ->
    println("#${ficha.numeroFicha}: ${ficha.pokemon.nombrePokemon}")
}

// joinToString - convertir lista a string
println(codigos.joinToString(", "))  // "PK1001, PK1002, PK2001"
```

### 23.6 Null Safety (Sección 3)

```kotlin
// En CentroPokemon.kt

// ?. (Safe Call) - hacer algo solo si no es null
val pokemon = camilla.estado as? EstadoCamilla.Ocupada
println(pokemon?.pokemon?.nombrePokemon)  // null si no está ocupada

// ?: (Elvis) - usar valor por defecto si es null
val costo = recaudacionPorCategoria[tipo] ?: 0.0  // 0.0 si no existe

// firstOrNull - obtener elemento o null
val libre = camillas.firstOrNull { it.estado is EstadoCamilla.Libre }
if (libre != null) {
    // Smart cast: libre ya no es null aquí
    println("Camilla ${libre.numero} disponible")
}
```

### 23.7 Funciones suspend y runBlocking (Sección 10)

```kotlin
// service/CentroPokemon.kt
suspend fun ingresarPokemon(pokemon: PokemonModel) {
    camilla.estado = EstadoCamilla.EnProceso(motivo = "Ingresando...")
    delay(3000)  // Pausa 3 segundos sin bloquear
    camilla.estado = EstadoCamilla.Ocupada(pokemon)
}

suspend fun darDeAlta(codigo: String) {
    camilla.estado = EstadoCamilla.EnProceso(motivo = "Procesando alta...")
    delay(6500)  // Pausa 6.5 segundos
    // ... cálculo de costos ...
    camilla.estado = EstadoCamilla.Libre
}

// Main.kt
fun main() = runBlocking {
    val centro = CentroPokemon()

    // Crear un Pokémon primero
    val pikachu = PokemonElectrico(
        idPokedex = "PK1001",
        nombrePokemon = "Pikachu",
        tipoEntrenador = TipoEntrenador.VIP,
        fechaIngreso = LocalDateTime.now()
    )

    centro.ingresarPokemon(pikachu)  // Puede llamar suspend
    centro.darDeAlta("PK1001")
}
```

### 23.8 Funciones de Extensión (Sección 14)

```kotlin
// Agregar método a una clase existente
fun CentroPokemon.resumen(): String {
    return "${this.nombre}: ${this.historial.size} atendidos, $" + "%.2f".format(this.recaudacionTotal)
}

// Uso
println(centro.resumen())  // "PokeCare Kanto Centro: 5 atendidos, $13238.75"
```

### 23.9 Scope Functions (Sección 15)

```kotlin
// apply - configurar objeto después de crearlo
val pikachu = PokemonElectrico(
    idPokedex = "PK1001",
    nombrePokemon = "Pikachu",
    tipoEntrenador = TipoEntrenador.VIP,
    fechaIngreso = LocalDateTime.now()
).apply {
    println("Pokémon $nombrePokemon creado con código $idPokedex")
}

// let - ejecutar código solo si no es null
val camillaLibre = centro.buscarCamillaLibre()
camillaLibre?.let {
    println("Camilla ${it.numero} encontrada")
}
```

---

## 24. Puntos Clave para el Examen

1. **`val` vs `var`**: `val` es inmutable (final), `var` es mutable
2. **Null Safety**: `?`, `?.`, `?:`, `!!` son operadores clave
3. **Herencia**: Usa `open` en la clase padre, `override` en el hijo
4. **Polimorfismo**: Un tipo padre puede referenciar hijos diferentes
5. **Enum**: Valores fijos con posibilidad de propiedades
6. **Data Class**: Para DTOs, genera equals/hashCode/toString
7. **Sealed Class**: Jerarquía restringida donde cada variante tiene datos diferentes
8. **Coroutines**: `suspend fun`, `delay()`, `runBlocking`, `launch`
9. **Lambda**: `{ parametro -> cuerpo }` o `{ it -> cuerpo }`
10. **Collections**: `filter`, `map`, `find`, `any`, `all`, `count`, `forEach`, `joinToString`
11. **LocalDateTime**: `LocalDateTime.now()` para obtener fecha/hora actual
12. **ChronoUnit**: `ChronoUnit.MINUTES.between(inicio, fin)` para calcular diferencias
13. **Extension Functions**: Agregar métodos a clases existentes
