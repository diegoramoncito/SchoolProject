package ec.gob.pichincha.turismopichincha.data.remote

import ec.gob.pichincha.turismopichincha.model.InformacionTuristica
import retrofit2.http.GET

interface MainApi {

    @GET("/tools/toGetInit.php")
    suspend fun getData():InformacionTuristica
}