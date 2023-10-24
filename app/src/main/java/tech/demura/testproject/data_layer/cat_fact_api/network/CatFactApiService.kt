package tech.demura.testproject.data_layer.cat_fact_api.network

import retrofit2.http.GET
import tech.demura.testproject.data_layer.cat_fact_api.dto.CatFactResponseDto

interface CatFactApiService {
    @GET("fact")
    suspend fun getRandomCatFact(): CatFactResponseDto
}