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

### 10.1 Concepto

Las corrutinas permiten ejecutar código de forma asíncrona sin bloquear el hilo principal.

### 10.2 Función Suspend

```kotlin
import kotlinx.coroutines.delay

suspend fun esperar() {
    delay(1000)  // Espera 1 segundo SIN bloquear
    println("Terminó")
}
```

### 10.3 runBlocking

```kotlin
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("Inicio")
    delay(1000)  // Solo funciona dentro de coroutine
    println("Fin")
}
```

### 10.4 launch (Ejecutar en paralelo)

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {  // Ejecuta en paralelo
        delay(1000)
        println("Tarea 1")
    }

    launch {
        delay(500)
        println("Tarea 2")
    }

    println("Inicio")
}
// Salida: Inicio → Tarea 2 → Tarea 1
```

### 10.5 async (Obtener resultado)

```kotlin
fun main() = runBlocking {
    val resultado = async {
        delay(1000)
        42
    }
    println("Resultado: ${resultado.await()}")  // Espera y obtiene el valor
}
```

### 10.6 Coroutines vs Thread

| Thread | Coroutine |
|--------|-----------|
| `Thread.sleep(1000)` bloquea | `delay(1000)` no bloquea |
| Pesado en memoria | Ligero en memoria |
| Difícil de controlar | Fácil de cancelar |

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

Kotlin permite sobrecargar ciertos operadores:

```kotlin
data class Pokemon(val nombre: String, val nivel: Int) {
    operator fun plus(otro: Pokemon): Pokemon {
        return Pokemon("Fusion", this.nivel + otro.nivel)
    }
}

val p1 = Pokemon("Pikachu", 10)
val p2 = Pokemon("Raichu", 20)
val fusion = p1 + p2  // Pokemon("Fusion", 30)
```

**Operadores sobrecargables:**
- `+` → `plus`
- `-` → `minus`
- `*` → `times`
- `/` → `div`
- `%` → `rem`
- `==` → `equals`
- `>` → `compareTo`

---

## 17. Generics

### 17.1 Clases Genéricas

```kotlin
class Contenedor<T>(var item: T)

val cajaString = Contenedor("Hola")
val cajaInt = Contenedor(42)
```

### 17.2 Funciones Genéricas

```kotlin
fun <T> imprimir(item: T) {
    println(item)
}

// Con restricción (bound)
fun <T : Comparable<T>> minimo(a: T, b: T): T {
    return if (a < b) a else b
}
```

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

## 21. Comparación Final Kotlin vs Java

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

## 22. Puntos Clave para el Examen

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
10. **Extension Functions**: Agregar métodos a clases existentes
