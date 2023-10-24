package tech.demura.testproject.data_layer.cat_image_api.network

import retrofit2.http.GET
import tech.demura.testproject.data_layer.cat_image_api.dto.CatImageResponseDto

interface CatImageApiService {
    @GET("cat?json=true")
    suspend fun getRandomCatImage(): CatImageResponseDto
}