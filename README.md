# PokeCare - Sistema de Gestión Médica Pokémon

## Descripción del Proyecto

**PokeCare** es un sistema de gestión médica para el Centro Pokémon "PokeCare Kanto Centro" desarrollado en Kotlin. Este proyecto es una actividad de Programación Orientada a Objetos (POO) para la asignatura de Desarrollo de Aplicaciones Móviles.

El sistema controla el ingreso y alta médica de Pokémon, aplica tarifas diferenciadas según la categoría del Pokémon y el perfil del entrenador, y genera reportes al cierre de cada turno de la Enfermera Joy.

---

## Requisitos Previos

### Software Necesario

| Programa | Versión Mínima | Link de Descarga |
|----------|----------------|------------------|
| JDK (Java Development Kit) | 17+ | [Adoptium](https://adoptium.net/) |
| IntelliJ IDEA | Community o Ultimate | [JetBrains](https://www.jetbrains.com/idea/) |
| Gradle | 8.0+ (se descarga automáticamente) | [Gradle](https://gradle.org/) |

### Conocimientos Previos

- Conceptos básicos de POO (clases, objetos, herencia)
- Conocimiento básico de Java (el proyecto compara Kotlin vs Java)
- Uso de terminal/consola básica

---

## Estructura del Proyecto

```
PokeCare/
├── build.gradle.kts                    # Configuración de Gradle
├── settings.gradle.kts                 # Nombre del proyecto
├── PROGRESO.md                         # Estado del proyecto
├── src/
│   └── main/
│       └── kotlin/
│           ├── Main.kt                 # Archivo principal (ejecución)
│           ├── model/                  # Modelos de datos
│           │   ├── PokemonModel.kt     # Clase base abstracta
│           │   ├── PokemonElectrico.kt # Subclase Eléctrico
│           │   ├── PokemonAgua.kt      # Subclase Agua
│           │   ├── PokemonDragon.kt    # Subclase Dragón
│           │   ├── TipoPokemon.kt      # Enum de categorías
│           │   ├── TipoEntrenador.kt   # Enum de entrenadores
│           │   ├── EstadoCamilla.kt    # Sealed class de estados
│           │   ├── CamillaModel.kt     # Modelo de camilla
│           │   └── FichaAlta.kt        # Data class para fichas de alta
│           └── service/                # Lógica de negocio
│               └── CentroPokemon.kt    # Gestor principal
```

---

## Guía Paso a Paso: Crear el Proyecto desde Cero

Esta guía asume que nunca has creado un proyecto en Kotlin. Sigue cada paso exactamente.

---

### PASO 0: Instalar el Software Necesario

#### 0.1 - Instalar JDK (Java Development Kit)

Kotlin se compila a bytecode de Java, así que necesitas un JDK.

1. Ir a [https://adoptium.net/](https://adoptium.net/)
2. Hacer click en **Latest Release**
3. Seleccionar tu sistema operativo (Windows/macOS/Linux)
4. Descargar el instalador `.msi` (Windows) o `.pkg` (macOS)
5. Ejecutar el instalador y seguir los pasos (click en "Next" en todo)
6. Verificar la instalación abriendo una terminal y escribiendo:
   ```
   java -version
   ```
   Debe mostrar algo como: `openjdk version "17.0.x"`

#### 0.2 - Instalar IntelliJ IDEA

1. Ir a [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/)
2. Descargar **Community Edition** (es gratis)
3. Ejecutar el instalador
4. En la instalación, marcar estas opciones:
   - ✅ Create Desktop Shortcut (64-bit)
   - ✅ Add "Open Folder as Project"
   - ✅ Add launchers dir to PATH
   - ✅ Associate .kt files
5. Click en **Install**
6. Abrir IntelliJ IDEA después de la instalación
7. Seleccionar **Dark theme** (o el que prefieras)
8. Click en **Skip Remaining Defaults**
9. Click en **Finish**

---

### PASO 1: Crear el Proyecto en IntelliJ IDEA

#### 1.1 - Abrir el diálogo de nuevo proyecto

1. Abrir IntelliJ IDEA
2. En la pantalla de inicio, hacer click en **New Project**
   - Si ya tienes un proyecto abierto: **File → New → Project**

#### 1.2 - Seleccionar tipo de proyecto

En el diálogo que aparece:

1. En el panel izquierdo, seleccionar **Kotlin**
2. En el panel derecho, seleccionar **JVM | Gradle - Kotlin DSL**
3. Hacer click en **Next**

#### 1.3 - Configurar el proyecto

En la siguiente pantalla, configurar:

| Campo | Valor | Explicación |
|-------|-------|-------------|
| **Name** | PokeCare | Nombre del proyecto |
| **Location** | Tu carpeta de trabajo | Ej: `C:\Users\TuUsuario\Documents\Kotlin\PokeCare` |
| **Build system** | Gradle (Kotlin DSL) | Sistema de compilación |
| **JDK** | 17 o superior | El JDK que instalaste |
| **Gradle DSL** | Kotlin | Formato del archivo de configuración |
| **Group** | cl.ejercicio | Identificador del paquete (como un namespace) |

> **¿Qué es Group?** Es como el paquete raíz de tu proyecto. En Java sería `package cl.ejercicio;`. Aquí se usa como identificador único.

4. Hacer click en **Create**

#### 1.4 - Esperar a que se configure el proyecto

1. IntelliJ mostrará "Loading project..." en la parte inferior
2. Esperar a que aparezca "BUILD SUCCESSFUL" en la consola
3. Esto puede tomar 1-2 minutos la primera vez (descarga dependencias)

#### 1.5 - Verificar la estructura

En el panel izquierdo (Project), debes ver:

```
PokeCare/
├── .git/
├── .gitignore
├── .gradle/
├── .idea/
├── build.gradle.kts
├── gradle/
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
└── src/
    └── main/
        └── kotlin/
            └── Main.kt
```

Si no ves la carpeta `src`, puede que IntelliJ la esté indexando. Espera un momento.

---

### PASO 2: Configurar build.gradle.kts

`build.gradle.kts` es el archivo de configuración del proyecto. Define las dependencias, plugins y cómo se compila.

#### 2.1 - Abrir el archivo

1. En el panel izquierdo, expandir la carpeta del proyecto
2. Hacer doble click en `build.gradle.kts`
3. Se abrirá en el editor

#### 2.2 - Reemplazar el contenido

Seleccionar todo el contenido (Ctrl+A) y reemplazar con:

```kotlin
plugins {
    kotlin("jvm") version "2.4.10"
    application
}

group = "cl.ejercicio"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}

application {
    mainClass.set("cl.ejercicio.MainKt")
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}
```

#### 2.3 - Explicación de cada línea

```kotlin
plugins {
    kotlin("jvm") version "2.4.10"    // Plugin de Kotlin para JVM
    application                        // Plugin para ejecutar la app
}
```
- `kotlin("jvm")`: Habilita Kotlin para compilar a bytecode de Java
- `application`: Permite ejecutar con `gradlew run`

```kotlin
group = "cl.ejercicio"    // Paquete raíz del proyecto
version = "1.0-SNAPSHOT"  // Versión del proyecto
```

```kotlin
repositories {
    mavenCentral()    // Desde dónde descargar dependencias
}
```
- `mavenCentral()`: Repositorio principal de librerías Java/Kotlin

```kotlin
dependencies {
    testImplementation(kotlin("test"))  // Librería de testing
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}
```
- `testImplementation`: Dependencia solo para tests
- `implementation`: Dependencia para el código principal
- `kotlinx-coroutines-core`: Librería para operaciones asíncronas (delay sin bloquear)

```kotlin
application {
    mainClass.set("cl.ejercicio.MainKt")  // Punto de entrada
}
```
- Indica que el método `main()` está en `Main.kt` del paquete `cl.ejercicio`

```kotlin
kotlin {
    jvmToolchain(25)  // Versión del JDK
}
```

```kotlin
tasks.test {
    useJUnitPlatform()  // Usar JUnit para tests
}
```

#### 2.4 - Sincronizar Gradle

Después de guardar el archivo (Ctrl+S), IntelliJ preguntará:

> "Gradle files have changed. Sync now?"

Hacer click en **Sync Now** (o el ícono de elefante 🐘 en la parte superior derecha).

Esperar a que termine la sincronización (puede tomar 1-2 minutos).

---

### PASO 3: Configurar settings.gradle.kts

#### 3.1 - Abrir el archivo

1. En el panel izquierdo, hacer doble click en `settings.gradle.kts`

#### 3.2 - Reemplazar el contenido

```kotlin
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
rootProject.name = "PokeCare"
```

**¿Qué hace?**
- `rootProject.name`: Nombre que aparecerá en IntelliJ
- El plugin resolve automáticamente la versión del JDK

---

### PASO 4: Crear la Estructura de Carpetas

Ahora necesitas crear las carpetas para organizar el código.

#### 4.1 - Crear carpeta model

1. En el panel izquierdo, expandir `src/main/kotlin`
2. Hacer click derecho en la carpeta `kotlin`
3. Seleccionar **New → Directory**
4. Escribir: `model`
5. Presionar Enter

#### 4.2 - Crear carpeta service

1. Hacer click derecho en la carpeta `kotlin`
2. Seleccionar **New → Directory**
3. Escribir: `service`
4. Presionar Enter

#### 4.3 - Verificar estructura

Deberías ver:

```
src/main/kotlin/
├── Main.kt
├── model/
└── service/
```

---

### PASO 5: Crear los Archivos de Código

Ahora vas a crear cada archivo .kt uno por uno.

#### 5.1 - Crear TipoPokemon.kt

1. Hacer click derecho en la carpeta `model`
2. Seleccionar **New → Kotlin Class/File**
3. En el diálogo, escribir: `TipoPokemon`
4. Seleccionar **Enum Class** (no Class)
5. Presionar Enter
6. IntelliJ creará el archivo con un enum vacío
7. Reemplazar todo el contenido con:

```kotlin
package cl.ejercicio.model

enum class TipoPokemon {
    ELECTRICO,
    AGUA,
    DRAGON
}
```

8. Guardar (Ctrl+S)

#### 5.2 - Crear TipoEntrenador.kt

1. Click derecho en `model` → **New → Kotlin Class/File**
2. Escribir: `TipoEntrenador`
3. Seleccionar **Enum Class**
4. Reemplazar contenido:

```kotlin
package cl.ejercicio.model

enum class TipoEntrenador {
    NOVATO,
    VIP,
    LEGENDARIO
}
```

#### 5.3 - Crear EstadoCamilla.kt (Sealed Class)

1. Click derecho en `model` → **New → Kotlin Class/File**
2. Escribir: `EstadoCamilla`
3. Seleccionar **Class** (no Enum Class)
4. Reemplazar contenido:

```kotlin
package cl.ejercicio.model

sealed class EstadoCamilla {
    object Libre : EstadoCamilla()
    data class Ocupada(val pokemon: PokemonModel) : EstadoCamilla()
    data class EnProceso(val motivo: String) : EstadoCamilla()
    data class FueraDeServicio(val motivo: String) : EstadoCamilla()
}
```

**¿Por qué sealed class y no enum?**

Cada estado de la camilla tiene **datos diferentes**:
- `Libre`: no necesita datos adicionales
- `Ocupada`: necesita saber qué Pokémon está en la camilla
- `EnProceso`: necesita un motivo ("Sanando...", "Ingresando...")
- `FueraDeServicio`: necesita un motivo (ej: "Desinfectando")

Un enum no puede tener propiedades diferentes para cada variante. Una sealed class sí.

**Uso con `when`:**
```kotlin
fun describirEstado(estado: EstadoCamilla): String {
    return when (estado) {
        is EstadoCamilla.Libre -> "Disponible"
        is EstadoCamilla.Ocupada -> "Ocupada por ${estado.pokemon.nombrePokemon}"
        is EstadoCamilla.EnProceso -> estado.motivo
        is EstadoCamilla.FueraDeServicio -> "Fuera de servicio: ${estado.motivo}"
    }
}
```

#### 5.4 - Crear PokemonModel.kt

1. Click derecho en `model` → **New → Kotlin Class/File**
2. Escribir: `PokemonModel`
3. Seleccionar **Class** (no Enum Class)
4. Reemplazar contenido:

```kotlin
package cl.ejercicio.model

import java.time.LocalDateTime

open class PokemonModel(
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
```

#### 5.5 - Crear PokemonElectrico.kt

1. Click derecho en `model` → **New → Kotlin Class/File**
2. Escribir: `PokemonElectrico`
3. Seleccionar **Class**
4. Reemplazar contenido:

```kotlin
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
```

#### 5.6 - Crear PokemonAgua.kt

1. Click derecho en `model` → **New → Kotlin Class/File**
2. Escribir: `PokemonAgua`
3. Seleccionar **Class**
4. Reemplazar contenido:

```kotlin
package cl.ejercicio.model

import java.time.LocalDateTime

class PokemonAgua(
    idPokedex: String,
    nombrePokemon: String,
    tipoEntrenador: TipoEntrenador,
    fechaIngreso: LocalDateTime
) : PokemonModel(idPokedex, nombrePokemon, TipoPokemon.AGUA, tipoEntrenador, fechaIngreso) {

    override fun calcularCosto(tiempoMinutos: Int): Double {
        if (tiempoMinutos < 30) {
            return 0.0
        }

        val horas = tiempoMinutos / 60.0
        return horas * 800.0
    }
}
```

#### 5.7 - Crear PokemonDragon.kt

1. Click derecho en `model` → **New → Kotlin Class/File**
2. Escribir: `PokemonDragon`
3. Seleccionar **Class**
4. Reemplazar contenido:

```kotlin
package cl.ejercicio.model

import java.time.LocalDateTime

class PokemonDragon(
    idPokedex: String,
    nombrePokemon: String,
    tipoEntrenador: TipoEntrenador,
    fechaIngreso: LocalDateTime,
    val megaEvolucionado: Boolean
) : PokemonModel(idPokedex, nombrePokemon, TipoPokemon.DRAGON, tipoEntrenador, fechaIngreso) {

    override fun calcularCosto(tiempoMinutos: Int): Double {
        val horas = tiempoMinutos / 60.0
        var costo = horas * 2500.0

        if (megaEvolucionado) {
            costo *= 1.30
        }

        return costo
    }
}
```

#### 5.8 - Crear CamillaModel.kt

1. Click derecho en `model` → **New → Kotlin Class/File**
2. Escribir: `CamillaModel`
3. Seleccionar **Class**
4. Reemplazar contenido:

```kotlin
package cl.ejercicio.model

class CamillaModel(
    val numero: Int,
    var estado: EstadoCamilla = EstadoCamilla.Libre
)
```

**Explicación:**
- `var estado`: Mutable (puede cambiar entre estados)
- `= EstadoCamilla.Libre`: Valor por defecto al crear la camilla
- No necesitamos campos separados para `pokemon` o `motivo` porque están **dentro** de la sealed class

**Antes (con enum):**
```kotlin
class CamillaModel(
    val numero: Int,
    var estado: EstadoCamilla = EstadoCamilla.LIBRE,
    var pokemon: PokemonModel? = null,  // Campo separado
    var motivo: String = ""             // Campo separado
)
```

**Ahora (con sealed class):**
```kotlin
class CamillaModel(
    val numero: Int,
    var estado: EstadoCamilla = EstadoCamilla.Libre
    // Pokemon y motivo están DENTRO de cada variante de EstadoCamilla
)
```

**Ventaja:** Los datos están donde deben estar. Si la camilla está libre, no tiene pokemon ni motivo. Si está ocupada, tiene pokemon. Si está en proceso, tiene motivo.

#### 5.9 - Crear FichaAlta.kt

1. Click derecho en `model` → **New → Kotlin Class/File**
2. Escribir: `FichaAlta`
3. Seleccionar **Class**
4. Reemplazar contenido:

```kotlin
package cl.ejercicio.model

data class FichaAlta(
    val numeroFicha: Int,
    val pokemon: PokemonModel,
    val tiempoMinutos: Int,
    val montoPagado: Double
)
```

**¿Qué es una Data Class?**

Una data class es una clase que solo contiene datos. Kotlin genera automáticamente:
- `equals()` - Comparar objetos
- `hashCode()` - Para usar en colecciones
- `toString()` - Representación en string
- `copy()` - Copiar objeto
- `componentN()` - Destructuring

**¿Para qué sirve FichaAlta?**

Cuando un Pokémon se da de alta, necesitamos guardar:
- Número de ficha secuencial
- El Pokémon que fue atendido
- Cuánto tiempo estuvo en tratamiento
- Cuánto pagó el entrenador

Esta información se usa en el reporte de cierre de turno.

#### 5.10 - Crear CentroPokemon.kt

1. Click derecho en `service` → **New → Kotlin Class/File**
2. Escribir: `CentroPokemon`
3. Seleccionar **Class**
4. Reemplazar contenido con TODO el código de la sección "Creando el Servicio" más abajo en este README

#### 5.11 - Editar Main.kt

1. Hacer doble click en `Main.kt` que ya existe
2. Reemplazar TODO el contenido con el código de la sección "Creando Main.kt" más abajo

---

### PASO 6: Verificar que Todo Compila

#### 6.1 - Buscar errores

1. En IntelliJ, ir a **Build → Build Project** (o presionar Ctrl+F9)
2. Esperar a que termine
3. Si todo está bien, verás "Build completed successfully" abajo
4. Si hay errores, aparecerán en rojo en la parte inferior

#### 6.2 - Errores comunes

| Error | Causa | Solución |
|-------|-------|----------|
| "Unresolved reference" | Falta un import | Agregar el import necesario arriba |
| "Type mismatch" | Tipo incorrecto | Verificar tipos de parámetros |
| "Package not found" | Paquete mal escrito | Verificar `package cl.ejercicio.model` |

---

### PASO 7: Ejecutar el Programa

#### 7.1 - Ejecutar con IntelliJ

1. Abrir `Main.kt`
2. Hacer click en el ícono verde ▶️ junto al `fun main()`
3. Seleccionar **Run 'MainKt'**
4. Esperar a que aparezca la consola con la salida

#### 7.2 - Ejecutar con Gradle (opcional)

1. Abrir la terminal de IntelliJ (**View → Tool Windows → Terminal**)
2. Escribir:
   ```
   .\gradlew.bat run
   ```
3. Esperar la ejecución

#### 7.3 - Salida esperada

Deberías ver algo como:

```
=== INGRESANDO POKÉMON ===
Camilla 1 - Ingresando Pikachu...
Pikachu ingresado en camilla 1
...
=== FICHA DE ALTA ===
Pokémon: Pikachu
Código: PK1001
Categoría: ELECTRICO
Tiempo: 75 minutos
Total: $1785.00
====================
...
========== REPORTE DE TURNO ==========
Centro: PokeCare Kanto Centro
Pokémon atendidos: 5
Recaudación total: $13238.75
...
=====================================
```

---

### PASO 8: Estructura Final del Proyecto

Después de crear todos los archivos, tu proyecto debe verse así:

```
PokeCare/
├── build.gradle.kts
├── settings.gradle.kts
├── src/
│   └── main/
│       └── kotlin/
│           ├── Main.kt
│           ├── model/
│           │   ├── TipoPokemon.kt
│           │   ├── TipoEntrenador.kt
│           │   ├── EstadoCamilla.kt
│           │   ├── PokemonModel.kt
│           │   ├── PokemonElectrico.kt
│           │   ├── PokemonAgua.kt
│           │   ├── PokemonDragon.kt
│           │   ├── CamillaModel.kt
│           │   └── FichaAlta.kt
│           └── service/
│               └── CentroPokemon.kt
```

¡Listo! Ya tienes el proyecto funcionando.

---

## Conceptos de Kotlin para Este Proyecto

### Variables: val vs var

En Kotlin hay dos tipos de variables:

```kotlin
// val = inmutable (no puede cambiar su valor, como final en Java)
val nombre = "Pikachu"    // ✅ Correcto
// nombre = "Raichu"      // ❌ Error: no se puede reasignar

// var = mutable (puede cambiar su valor)
var vida = 100
vida = 50                 // ✅ Correcto
```

**Comparación con Java:**
```java
// Java
final String nombre = "Pikachu";   // Equivale a val
String nombre = "Pikachu";         // Equivale a var
```

### Tipos de Variables

```kotlin
val texto: String = "Hola"           // Texto
val entero: Int = 42                 // Número entero
val decimal: Double = 3.14           // Número decimal
val verdadero: Boolean = true        // Verdadero/Falso
val letra: Char = 'A'               // Un solo carácter
```

**Diferencia con Java:** En Kotlin no necesitas escribir el tipo si Kotlin puede inferirlo:
```kotlin
val nombre = "Pikachu"    // Kotlin sabe que es String
val vida = 100            // Kotlin sabe que es Int
```

### String Templates (Plantillas de String)

En Kotlin puedes insertar variables directamente en strings con `$`:

```kotlin
val nombre = "Pikachu"
val nivel = 15

// Usando $
println("El Pokémon es $nombre y tiene nivel $nivel")

// Usando ${} para expresiones
println("El Pokémon es $nombre y tiene ${nivel * 2} puntos de ataque")
```

**Comparación con Java:**
```java
// Java
System.out.println("El Pokémon es " + nombre + " y tiene nivel " + nivel);
```

---

## Creando los Enums

### ¿Qué es un Enum?

Un enum (enumeración) es un tipo de datos que representa un conjunto fijo de valores constantes. Es como una lista de opciones que no cambia.

### Enum: TipoPokemon

Crear archivo `model/TipoPokemon.kt`:

```kotlin
package cl.ejercicio.model

enum class TipoPokemon {
    ELECTRICO,
    AGUA,
    DRAGON
}
```

**Explicación:**
- `enum class`: Declara una enumeración
- Los valores van en mayúsculas por convención
- Cada valor separado por coma

**Uso:**
```kotlin
val tipo = TipoPokemon.ELECTRICO
println(tipo)  // Imprime: ELECTRICO
```

**Comparación con Java:**
```java
// Java
public enum TipoPokemon {
    ELECTRICO,
    AGUA,
    DRAGON
}
```

### Enum: TipoEntrenador

Crear archivo `model/TipoEntrenador.kt`:

```kotlin
package cl.ejercicio.model

enum class TipoEntrenador {
    NOVATO,
    VIP,
    LEGENDARIO
}
```

### Sealed Class: EstadoCamilla

Crear archivo `model/EstadoCamilla.kt`:

```kotlin
package cl.ejercicio.model

sealed class EstadoCamilla {
    object Libre : EstadoCamilla()
    data class Ocupada(val pokemon: PokemonModel) : EstadoCamilla()
    data class EnProceso(val motivo: String) : EstadoCamilla()
    data class FueraDeServicio(val motivo: String) : EstadoCamilla()
}
```

**¿Por qué sealed class y no enum?**

Cada estado tiene **datos diferentes**:
- `Libre`: no necesita datos adicionales (es un objeto único)
- `Ocupada`: necesita saber qué Pokémon está en la camilla
- `EnProceso`: necesita un motivo ("Sanando...", "Ingresando...")
- `FueraDeServicio`: necesita un motivo

Un enum no puede tener propiedades diferentes para cada variante.

**Uso con `when`:**
```kotlin
fun describirEstado(estado: EstadoCamilla): String {
    return when (estado) {
        is EstadoCamilla.Libre -> "Disponible"
        is EstadoCamilla.Ocupada -> "Ocupada por ${estado.pokemon.nombrePokemon}"
        is EstadoCamilla.EnProceso -> estado.motivo
        is EstadoCamilla.FueraDeServicio -> "Fuera de servicio: ${estado.motivo}"
    }
}
```

**Comparación con Java:**
```java
// Java - usaría una jerarquía de clases abstractas
public abstract class EstadoCamilla {
    public static class Libre extends EstadoCamilla {}
    public static class Ocupada extends EstadoCamilla {
        private final Pokemon pokemon;
        public Ocupada(Pokemon pokemon) { this.pokemon = pokemon; }
    }
    public static class EnProceso extends EstadoCamilla {
        private final String motivo;
        public EnProceso(String motivo) { this.motivo = motivo; }
    }
}
```

---

## Creando las Clases

### ¿Qué es una Clase?

Una clase es un plano/plantilla para crear objetos. Define propiedades (datos) y métodos (acciones).

### Clase Base: PokemonModel

Crear archivo `model/PokemonModel.kt`:

```kotlin
package cl.ejercicio.model

import java.time.LocalDateTime

open class PokemonModel(
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
```

**Explicación:**
- `open class`: Puede ser heredada por otras clases (en Java todas son open por defecto)
- Constructor primario: Los paréntesis después del nombre definen las propiedades
- `val`: Cada parámetro se convierte en una propiedad pública
- `open fun`: Método que puede ser sobreescrito en subclases

**¿Por qué `open`?** En Kotlin, las clases y métodos son `final` por defecto (no se pueden heredar). Debes usar `open` explícitamente.

**Comparación con Java:**
```java
// Java
public class PokemonModel {
    private final String idPokedex;
    private final String nombrePokemon;
    // ... otros campos

    public PokemonModel(String idPokedex, String nombrePokemon, ...) {
        this.idPokedex = idPokedex;
        this.nombrePokemon = nombrePokemon;
    }

    public double calcularCosto(int tiempoMinutos) {
        return 0.0;
    }

    // Getters...
}
```

### Subclase: PokemonElectrico

Crear archivo `model/PokemonElectrico.kt`:

```kotlin
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
```

**Explicación:**
- `: PokemonModel(...)`: Hereda de PokemonModel
- `override fun`: Sobreescribe el método de la clase padre
- `TipoPokemon.ELECTRICO`: Se pasa directamente al constructor padre
- **Tarifa**: $1,500/hora con 20% descuento para VIP

**Herencia en Kotlin vs Java:**
```java
// Java
public class PokemonElectrico extends PokemonModel {
    public PokemonElectrico(...) {
        super(idPokedex, nombrePokemon, TipoPokemon.ELECTRICO, ...);
    }

    @Override
    public double calcularCosto(int tiempoMinutos) {
        // ...
    }
}
```

### Subclase: PokemonAgua

Crear archivo `model/PokemonAgua.kt`:

```kotlin
package cl.ejercicio.model

import java.time.LocalDateTime

class PokemonAgua(
    idPokedex: String,
    nombrePokemon: String,
    tipoEntrenador: TipoEntrenador,
    fechaIngreso: LocalDateTime
) : PokemonModel(idPokedex, nombrePokemon, TipoPokemon.AGUA, tipoEntrenador, fechaIngreso) {

    override fun calcularCosto(tiempoMinutos: Int): Double {
        if (tiempoMinutos < 30) {
            return 0.0
        }

        val horas = tiempoMinutos / 60.0
        return horas * 800.0
    }
}
```

**Regla especial**: Si el tiempo es menor a 30 minutos, el costo es $0.

### Subclase: PokemonDragon

Crear archivo `model/PokemonDragon.kt`:

```kotlin
package cl.ejercicio.model

import java.time.LocalDateTime

class PokemonDragon(
    idPokedex: String,
    nombrePokemon: String,
    tipoEntrenador: TipoEntrenador,
    fechaIngreso: LocalDateTime,
    val megaEvolucionado: Boolean
) : PokemonModel(idPokedex, nombrePokemon, TipoPokemon.DRAGON, tipoEntrenador, fechaIngreso) {

    override fun calcularCosto(tiempoMinutos: Int): Double {
        val horas = tiempoMinutos / 60.0
        var costo = horas * 2500.0

        if (megaEvolucionado) {
            costo *= 1.30
        }

        return costo
    }
}
```

**Propiedad adicional**: `megaEvolucionado: Boolean` - Solo los Pokémon Dragón tienen esta propiedad. Si es `true`, se aplica un recargo del 30%.

### Clase: CamillaModel

Crear archivo `model/CamillaModel.kt`:

```kotlin
package cl.ejercicio.model

class CamillaModel(
    val numero: Int,
    var estado: EstadoCamilla = EstadoCamilla.Libre
)
```

**Explicación:**
- `var estado`: Mutable (puede cambiar entre estados)
- `= EstadoCamilla.Libre`: Valor por defecto al crear la camilla
- No necesitamos campos separados para `pokemon` o `motivo` porque están **dentro** de la sealed class

**¿Por qué no hay campo `pokemon` o `motivo`?**

Con la sealed class, los datos están donde deben estar:
```kotlin
// Si está libre → no tiene datos extra
camilla.estado = EstadoCamilla.Libre

// Si está ocupada → tiene el Pokémon DENTRO del estado
camilla.estado = EstadoCamilla.Ocupada(pokemon = pikachu)

// Si está en proceso → tiene el motivo DENTRO del estado
camilla.estado = EstadoCamilla.EnProceso(motivo = "Sanando...")

// Acceder al Pokémon (usando smart cast)
when (camilla.estado) {
    is EstadoCamilla.Ocupada -> {
        val pokemon = (camilla.estado as EstadoCamilla.Ocupada).pokemon
        println("Camilla ${camilla.numero} tiene a ${pokemon.nombrePokemon}")
    }
    // ... otros casos
}
```

---

## Creando el Servicio: CentroPokemon

Crear carpeta `service/` y archivo `service/CentroPokemon.kt`:

```kotlin
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
    // ... métodos
}
```

**Explicación de propiedades:**
- `mutableListOf<CamillaModel>()`: Lista mutable (puede agregar/quitar elementos)
- `mutableListOf<FichaAlta>()`: Lista para guardar las fichas de alta de cada Pokémon
- `mutableMapOf<TipoPokemon, Double>()`: Mapa/diccionario clave-valor
- `init { }`: Bloque de inicialización (se ejecuta al crear la instancia)
- `contadorFichas`: Contador para numerar las fichas de alta secuencialmente

### Método: Generar ID Automático

```kotlin
fun generarId(): String {
    contadorIds++
    return "PK" + contadorIds.toString().padStart(4, '0')
}
```

**Explicación:**
- `toString()`: Convierte el número a string
- `padStart(4, '0')`: Rellena con ceros a la izquierda hasta 4 caracteres
- Resultado: "PK0001", "PK0002", etc.

### Método: Validar Código

```kotlin
fun validarCodigo(codigo: String): Boolean {
    val regex = Regex("^PK\\d{4}$")
    return regex.matches(codigo)
}
```

**Explicación:**
- `Regex`: Expresión regular
- `^PK`: Empieza con "PK"
- `\\d{4}`: Exactamente 4 dígitos
- `$`: Fin del string

### Método: Buscar Camilla Libre

```kotlin
fun buscarCamillaLibre(): CamillaModel? {
    return camillas.firstOrNull {
        it.estado is EstadoCamilla.Libre
    }
}
```

**Explicación:**
- `firstOrNull`: Retorna el primer elemento que cumple la condición, o null si no hay ninguno
- `it`: Se refiere a cada elemento de la lista (como `x` en un for)
- `is EstadoCamilla.Libre`: Verifica si el estado es de tipo Libre (usando smart cast)

**Comparación con Java:**
```java
// Java
return camillas.stream()
    .filter(c -> c.getEstado() instanceof Libre)
    .findFirst()
    .orElse(null);
```

### Método: Ingresar Pokémon (Corrutinas)

```kotlin
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

    camilla.estado = EstadoCamilla.EnProceso(motivo = "Ingresando Pokémon...")
    println("Camilla ${camilla.numero} - Ingresando ${pokemon.nombrePokemon}...")

    delay(3000)  // 3 segundos

    camilla.estado = EstadoCamilla.Ocupada(pokemon = pokemon)
    historial.add(pokemon)

    println("${pokemon.nombrePokemon} ingresado en camilla ${camilla.numero}")
}
```

**Explicación:**
- `suspend fun`: Función que puede pausarse sin bloquear el hilo
- `delay(3000)`: Espera 3 segundos SIN bloquear (a diferencia de Thread.sleep)
- `return`: Sale del método si hay error

**¿Por qué `suspend`?** Las corrutinas permiten que el programa siga ejecutándose mientras espera. Si usaras `Thread.sleep(3000)`, la aplicación se congelaría durante 3 segundos.

**Cambio con sealed class:**
- Antes: `camilla.estado = EstadoCamilla.EN_PROCESO` + `camilla.motivo = "..."`
- Ahora: `camilla.estado = EstadoCamilla.EnProceso(motivo = "...")`

### Método: Dar de Alta Pokémon

```kotlin
suspend fun darDeAlta(codigo: String) {
    if (!validarCodigo(codigo)) {
        println("Error: codigo invalido - $codigo")
        return
    }

    // Buscar camilla que tenga el Pokémon con ese código
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

    delay(6500)  // 6.5 segundos

    // Calcular tiempo de tratamiento automáticamente
    val tiempoMinutos = ChronoUnit.MINUTES.between(pokemon.fechaIngreso, LocalDateTime.now()).toInt()

    // Cálculo del costo
    val costoBase = pokemon.calcularCosto(tiempoMinutos)
    val conIVA = costoBase * 1.19
    val total = if (pokemon.tipoEntrenador == TipoEntrenador.LEGENDARIO) conIVA * 0.50 else conIVA

    // Actualizar recaudación
    recaudacionTotal += total
    recaudacionPorCategoria[pokemon.tipoPokemon] =
        (recaudacionPorCategoria[pokemon.tipoPokemon] ?: 0.0) + total

    // Crear ficha de alta
    contadorFichas++
    val ficha = FichaAlta(
        numeroFicha = contadorFichas,
        pokemon = pokemon,
        tiempoMinutos = tiempoMinutos,
        montoPagado = total
    )
    fichasDeAlta.add(ficha)

    // Liberar camilla
    camilla.estado = EstadoCamilla.Libre

    // Mostrar ficha
    println("""
        === FICHA DE ALTA #$contadorFichas ===
        Pokémon: ${pokemon.nombrePokemon}
        Código: ${pokemon.idPokedex}
        Categoría: ${pokemon.tipoPokemon}
        Tiempo: $tiempoMinutos minutos
        Total: \$${"%.2f".format(total)}
        ====================
    """.trimIndent())
}
```

**Explicación del cálculo:**
1. `ChronoUnit.MINUTES.between(pokemon.fechaIngreso, LocalDateTime.now())`: Calcula los minutos entre la fecha de ingreso y ahora
2. `pokemon.calcularCosto(tiempoMinutos)`: Calcula el costo base según la categoría
3. `costoBase * 1.19`: Agrega IVA del 19%
4. Si es Legendario: `conIVA * 0.50` (50% descuento)

**¿Por qué usar ChronoUnit?**
- Calcula automáticamente el tiempo real de tratamiento
- No depende de un valor manual que pueda ser incorrecto
- Usa la fecha de ingreso que se registró al inicio

**Cambio con sealed class:**
- Para buscar el Pokémon: se verifica `is EstadoCamilla.Ocupada` y se extrae el pokemon
- Para liberar: `camilla.estado = EstadoCamilla.Libre` (sin necesidad de limpiar campos)

**Explicación de `?:` (Elvis Operator):**
```kotlin
recaudacionPorCategoria[pokemon.tipoPokemon] ?: 0.0
// Si es null, usa 0.0; si no, usa el valor
```

### Métodos de Consulta

```kotlin
fun contarCamillasDisponibles(): Int {
    return camillas.count { it.estado is EstadoCamilla.Libre }
}

fun filtrarPokemonVIP(): List<PokemonModel> {
    return historial.filter { it.tipoEntrenador == TipoEntrenador.VIP }
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
```

**Métodos de Kotlin Collections:**
- `count {}`: Cuenta elementos que cumplen condición
- `filter {}`: Filtra elementos, retorna nueva lista
- `maxByOrNull {}`: Retorna el mayor según un criterio, o null si está vacío
- `map {}`: Transforma cada elemento y retorna una nueva lista
- `joinToString()`: Convierte una lista a string separado por comas

### Método: Generar Reporte

```kotlin
fun generarReporte() {
    println("""
        ========== REPORTE DE TURNO ==========
        Centro: $nombre
        Pokémon atendidos: ${historial.size}
        Recaudación total: \$${"%.2f".format(recaudacionTotal)}
        Costo promedio: \$${"%.2f".format(calcularCostoPromedio())}
        Camillas disponibles: ${contarCamillasDisponibles()}
        
        --- Detalle por Pokémon ---
    """.trimIndent())

    for (ficha in fichasDeAlta) {
        println("#${ficha.numeroFicha} | ${ficha.pokemon.tipoPokemon} | ${ficha.pokemon.idPokedex} | ${ficha.tiempoMinutos} min | \$${"%.2f".format(ficha.montoPagado)}")
    }

    println("")
    println("--- Recaudación por categoría ---")
    for ((tipo, monto) in recaudacionPorCategoria) {
        println("$tipo: \$${"%.2f".format(monto)}")
    }

    val categoriaMasIngresos = obtenerCategoriaMasIngresos()
    if (categoriaMasIngresos != null) {
        println("\nCategoría con más ingresos: $categoriaMasIngresos")
    }

    println("")
    println("--- Pokémon VIP atendidos ---")
    val vipList = filtrarPokemonVIP()
    if (vipList.isEmpty()) {
        println("Ninguno")
    } else {
        for (p in vipList) {
            println("${p.nombrePokemon} (${p.idPokedex})")
        }
    }

    println("")
    println("--- Códigos dados de alta ---")
    val codigos = obtenerCodigosDadosDeAlta()
    if (codigos.isEmpty()) {
        println("Ninguno")
    } else {
        println(codigos.joinToString(", "))
    }

    println("=====================================")
}
```

**Explicación:**
- `trimIndent()`: Elimina la sangría común de strings multilínea
- `"$"%.2f".format(total)"`: Formatea número con 2 decimales
- `for ((tipo, monto) in recaudacionPorCategoria)`: Itera sobre un mapa
- `for (ficha in fichasDeAlta)`: Muestra el detalle de cada Pokémon atendido
- `joinToString(", ")`: Convierte la lista a string separado por comas

---

## Creando Main.kt

Crear archivo `Main.kt`:

```kotlin
package cl.ejercicio

import cl.ejercicio.model.*
import cl.ejercicio.service.CentroPokemon
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

fun main() = runBlocking {
    val centro = CentroPokemon()

    // Crear Pokémon
    val pikachu = PokemonElectrico(
        idPokedex = "PK1001",
        nombrePokemon = "Pikachu",
        tipoEntrenador = TipoEntrenador.VIP,
        fechaIngreso = LocalDateTime.now()
    )

    // ... otros Pokémon

    // Ingresar Pokémon
    centro.ingresarPokemon(pikachu)

    // Dar de alta
    centro.darDeAlta("PK1001")

    // Reporte
    centro.generarReporte()
}
```

**Explicación:**
- `runBlocking`: Crea un scope de corrutinas para poder llamar `suspend fun`
- `LocalDateTime.now()`: Obtiene la fecha y hora actual
- Import con `*`: Importa todo del paquete model

---

## Ejecutar el Proyecto

### Opción 1: Usando Gradle (Recomendado)

En terminal, dentro de la carpeta del proyecto:

```bash
# Windows
gradlew.bat run

# macOS/Linux
./gradlew run
```

### Opción 2: Usando IntelliJ IDEA

1. Abrir `Main.kt`
2. Click en el ícono verde ▶️ junto al método `main`
3. Seleccionar **Run 'MainKt'**

---

## Reglas de Negocio

### R1 - Categorías de Pokémon

| Categoría | Tarifa Base | Regla Especial |
|-----------|-------------|----------------|
| Eléctrico | $1,500/hora | VIP: 20% descuento |
| Agua | $800/hora | < 30 min = $0 |
| Dragón | $2,500/hora | Mega: 30% recargo |

### R2 - Estados de Camillas

| Estado | Descripción |
|--------|-------------|
| LIBRE | Disponible para nuevo Pokémon |
| OCUPADA | Tiene Pokémon asignado |
| EN_PROCESO | Esperando máquina sanadora |
| FUERA_DE_SERVICIO | Inhabilitada |

### R3 - Cálculo de Tarifas

```
1. costoBase = pokemon.calcularCosto(tiempo)
2. conIVA = costoBase × 1.19
3. Si Legendario → conIVA × 0.50
4. Total = resultado
```

### R4 - Consultas de Negocio

- ¿Cuántas camillas disponibles?
- ¿Qué Pokémon son de entrenadores VIP?
- ¿Cuál es el costo promedio?
- ¿Qué Pokémon estuvo más tiempo en tratamiento?
- Reporte de cierre de turno

### R5 - Operaciones Asíncronas

- Ingreso: delay 3 segundos
- Alta: delay 6.5 segundos
- Estado "En Proceso" durante la espera

### R6 - Manejo de Errores

| Error | Mensaje |
|-------|---------|
| Código inválido | "Error: código inválido - [código]" |
| Centro lleno | "Error: centro sin capacidad" |
| Pokémon no encontrado | "Error: Pokémon no encontrado - [código]" |

### R7 - Organización Técnica

- Modelos en `model/`
- Enums en `model/`
- Lógica en `service/`
- Ejecución en `Main.kt`

---

## Datos de Prueba

### Pokémon a Ingresar

| Tipo | Código | Nombre | Entrenador | Extra |
|------|--------|--------|------------|-------|
| Eléctrico | PK1001 | Pikachu | VIP | - |
| Eléctrico | PK1002 | Raichu | Novato | - |
| Agua | PK2001 | Squirtle | Novato | - |
| Dragón | PK3001 | Dragonite | Legendario | Mega: Sí |
| Dragón | PK3002 | Altaria | Novato | Mega: No |

### Tiempos de Tratamiento

| Código | Minutos | Resultado Esperado |
|--------|---------|-------------------|
| PK1001 | 75 | $1,785.00 |
| PK1002 | 180 | $5,355.00 |
| PK2001 | 25 | $0.00 |
| PK3001 | 120 | $3,867.50 |
| PK3002 | 45 | $2,231.25 |
| **Total** | | **$13,238.75** |

### Prueba de Error

- Código "123ABC" → Debe mostrar error de formato inválido

---

## Glossario: Kotlin vs Java

| Concepto | Java | Kotlin |
|----------|------|--------|
| Crear objeto | `new Pokemon(...)` | `Pokemon(...)` |
| Variable final | `final String x = "hi"` | `val x = "hi"` |
| Variable mutable | `String x = "hi"` | `var x = "hi"` |
| Null check | `if (x != null)` | `if (x != null)` o `x?.let {}` |
| String concatenation | `"Hola " + nombre` | `"Hola $nombre"` |
| Getter/Setter | `getX()` / `setX()` | `obj.x` |
| Herencia | `extends` | `:` |
| Override | `@Override` | `override` |
| Switch | `switch` | `when` |
| Coroutines | `Thread.sleep()` | `delay()` |
| Lists | `ArrayList<>()` | `mutableListOf()` |
| Maps | `HashMap<>()` | `mutableMapOf()` |
| Stream filter | `stream().filter().collect()` | `filter {}` |
| Null safety | Nullable by default | `?` for nullable |

---

## Solución de Errores Comunes

### Error: "Unresolved reference"

**Causa:** Falta un import o el paquete es incorrecto.

**Solución:** Agregar el import necesario:
```kotlin
import cl.ejercicio.model.TipoPokemon
```

### Error: "Type mismatch"

**Causa:** Estás pasando un tipo incorrecto a un método.

**Solución:** Verificar los tipos de los parámetros.

### Error: "Cannot access 'delay'"

**Causa:** Falta la dependencia de corrutinas.

**Solución:** Verificar `build.gradle.kts` tenga:
```kotlin
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
```

### Error: "Suspend function 'delay' can be called only from coroutine"

**Causa:** Estás llamando `delay()` fuera de un `suspend fun` o `runBlocking`.

**Solución:** Envolver en `runBlocking {}`:
```kotlin
fun main() = runBlocking {
    delay(1000)
}
```

---

## Licencia

Proyecto educativo para la asignatura de Desarrollo de Aplicaciones Móviles.
