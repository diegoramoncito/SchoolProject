package ec.gob.pichincha.turismopichincha.model

data class InformacionTuristica(
    val buscados: List<Buscado>,
    val eventos: List<Any>,
    val recomendados: List<Recomendado>
)