package cl.ejercicio.model

enum class EstadoCamilla (val descripcion: String){

    LIBRE("La camilla está disponible"),
    OCUPADA("La camilla tiene un Pokemon asignado"),
    EN_PROCESO ("Etapa de escaneo o sanacion"),
    FUERA_SERVICIO ("La camilla está inhabilitada o desinfectandose")

}