# PokeCare - Progreso del Proyecto

## Contexto General
Sistema de gestión médica para el Centro Pokémon "PokeCare Kanto Centro" desarrollado en Kotlin.
Actividad de POO para la asignatura de Desarrollo de Aplicaciones Móviles.

## Archivo de Instrucciones
`ejercicio tipo eva 1.docx` - Contiene todos los requisitos (R1-R7) del sistema.

---

## Estructura del Proyecto

```
src/main/kotlin/
├── Main.kt                          (vacío - pendiente)
├── model/
│   ├── PokemonModel.kt              ✅ Clase base open
│   ├── PokemonElectrico.kt          ✅ Subclase - tiene calcularCosto()
│   ├── PokemonAgua.kt               ✅ Subclase - tiene calcularCosto()
│   ├── PokemonDragon.kt             ✅ Subclase - tiene calcularCosto()
│   ├── TipoPokemon.kt               ✅ Enum (ELECTRICO, AGUA, DRAGON)
│   ├── TipoEntrenador.kt            ✅ Enum (NOVATO, VIP, LEGENDARIO)
│   ├── EstadoCamilla.kt             ✅ Sealed Class (Libre, Ocupada, EnProceso, FueraDeServicio)
│   └── CamillaModel.kt              ✅ Modelo de camilla con estado sealed
└── service/
    └── CentroPokemon.kt             ✅ Gestor principal con corrutinas
```

---

## Estado de Requisitos

### R1 - Categorías de Pokémon y reglas de cobro ✅ PARCIAL
- [x] Crear enum TipoPokemon con 3 categorías
- [x] Crear enum TipoEntrenador con 3 tipos
- [x] Crear clase base PokemonModel con datos comunes
- [x] Crear subclases por tipo (Electrico, Agua, Dragon)
- [x] Implementar lógica de cobro por categoría en cada subclase
- [ ] Generar ID automático "PK" + 4 dígitos (PK0001, PK0002...)
  - Decisión: usar contador en el gestor del centro para generar IDs automáticamente
  - No se validará, se generará directamente con formato correcto

### R2 - Estados de camillas ✅ COMPLETADO
- [x] Sealed class EstadoCamilla con 4 estados
- [x] Clase CamillaModel con estado usando sealed class
- [x] Cada estado lleva sus propios datos (pokemon en Ocupada, motivo en EnProceso/FueraDeServicio)

### R3 - Cálculo de tarifas y validación ❌ NO INICIADO
Pendiente:
- Aplicar IVA 19% después de calcularCosto()
- Aplicar 50% descuento si entrenador es Legendario
- Validaciones de datos

### R4 - Gestión del catálogo y consultas ❌ NO INICIADO
Pendiente:
- Contar camillas disponibles
- Filtrar Pokémon VIP
- Calcular costo promedio
- Listar códigos Pokédex de dados de alta
- Encontrar Pokémon con más tiempo en tratamiento
- Reporte de cierre de turno

### R5 - Registro asíncrono (Corrutinas) ❌ NO INICIADO
Pendiente:
- Usar corrutinas para ingreso (3 seg delay) y alta (6.5 seg delay)
- Estados "En Proceso" durante operaciones

### R6 - Comportamiento ante errores ❌ NO INACHIADO
Pendiente:
- Código Pokédex inválido
- Resultado de tarifa inválido
- Pokémon no encontrado
- Centro sin capacidad

### R7 - Organización técnica ✅ COMPLETADO
- [x] Modelos en separate files
- [x] Enums en separate files
- [x] Sealed Class para EstadoCamilla
- [x] CentroPokemon como gestor de camillas
- [ ] Main.kt con pruebas

---

## Lógica de Costos Implementada

### PokemonElectrico ($1,500/hora)
```
horas = minutos / 60.0
costo = horas * 1500
Si VIP → costo * 0.80 (20% descuento)
```

### PokemonAgua ($800/hora)
```
Si minutos < 30 → costo = $0
Si no → horas * 800
```

### PokemonDragon ($2,500/hora)
```
horas = minutos / 60.0
costo = horas * 2500
Si Mega-Evolucionado → costo * 1.30 (30% recargo)
```

### Cálculo total (pendiente implementar en Main o CentroPokemon)
```
1. costoBase = pokemon.calcularCosto(tiempo)
2. conIVA = costoBase * 1.19
3. Si Legendario → conIVA * 0.50
4. Total = resultado
```

---

## Próximos Pasos a Realizar

1. **Implementar Main.kt** con datos de prueba del documento
2. **Probar el sistema** completo con los 5 Pokémon de prueba
3. **Verificar cálculos** de costos con los tiempos indicados

---

## Datos de Prueba (del documento)

### Pokémon a ingresar:
| Tipo | Código | Nombre | Entrenador | Extra |
|------|--------|--------|------------|-------|
| Eléctrico | PK1001 | Pikachu | VIP | - |
| Eléctrico | PK1002 | Raichu | Novato | - |
| Agua | PK2001 | Squirtle | Novato | - |
| Dragón | PK3001 | Dragonite | Legendario | Mega: Sí |
| Dragón | PK3002 | Altaria | Novato | Mega: No |

### Tiempos de tratamiento para altas:
| Código | Minutos | Cálculo esperado |
|--------|---------|------------------|
| PK1001 | 75 min | Eléctrico VIP con descuento |
| PK1002 | 180 min | Eléctrico Novato sin descuento |
| PK2001 | 25 min | Agua < 30 min = $0 |
| PK3001 | 120 min | Dragón Mega Legendario |
| PK3002 | 45 min | Dragón normal Novato |

### Prueba de error:
- Código "123ABC" → Formato inválido

---

## Decisiones Tomadas
1. **Herencia/Polimorfismo**: Se usan subclases (PokemonElectrico, PokemonAgua, PokemonDragon) en vez de Service
2. **ID automático**: Se genera con contador en CentroPokemon (PK0001, PK0002...)
3. **Clase base open**: PokemonModel es open para poder ser heredada
4. **Método calcularCosto**: Se sobreescribe en cada subclase
5. **Sealed Class para EstadoCamilla**: Cada estado lleva sus propios datos (pokemon, motivo)
6. **CamillaModel simplificado**: Solo tiene numero y estado (los datos están en la sealed class)

## Notas para la IA
- El usuario prefiere que se le explique la lógica y él escriba el código
- Si se pide corrección explícita, se puede editar el archivo
- El usuario está aprendiendo POO, explicar conceptos cuando se pregunte
