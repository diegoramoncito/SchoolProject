package ec.gob.pichincha.turismopichincha.model

data class Buscado(
    val actividades: List<Actividade>,
    val calificacion: Int,
    val canton: String,
    val comentario: String,
    val descripcion: String,
    val dificultad: String,
    val fotos: List<String>,
    val id: Int,
    val links: List<Link>,
    val parroquia: String,
    val presupuesto: String,
    val servicios: List<Servicio>,
    val subtitulo: String,
    val telefono: String,
    val temperatura: String,
    val tipo: String,
    val titulo: String
)