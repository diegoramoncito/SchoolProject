package ec.gob.pichincha.turismopichincha.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InformacionTuristica(
    val eventos:List<EventoIndividual>,
    val recomendados:List<DestinoIndividual>,
    val buscados:List<DestinoIndividual>
)

data class EventoIndividual(
    val id: Int,
    val calificacion: Double,
    val titulo: String,
    val subtitulo: String,
    val descripcion: String,
    val temperatura: String,
    val dificultad: String,
    val presupuesto: String,
    val fotos:List<String>,
    val actividades:List<ActividadIndividual>,
    val servicios:List<ServicioIndividual>,
    val links:List<LinkIndividual>,
    val telefono:String,
    val comentario :String,
    val destinos:List<DestinoIndividual>?
)

data class DestinoIndividual(
    val id: Int,
    val calificacion: Double,
    val titulo: String,
    val subtitulo: String,
    val descripcion: String,
    val temperatura: String,
    val dificultad: String,
    val presupuesto: String,
    val fotos:List<String>,
    val actividades:List<ActividadIndividual>,
    val servicios:List<ServicioIndividual>,
    val links:List<LinkIndividual>,
    val telefono:String,
    val comentario :String
)
data class ActividadIndividual(
    val tipo:Int,
    val leyenda:String
)
data class ServicioIndividual(
    val tipo:Int,
    val leyenda:String
)
data class LinkIndividual(
    val tipo:Int,
    val url:String,
    val leyenda:String
)