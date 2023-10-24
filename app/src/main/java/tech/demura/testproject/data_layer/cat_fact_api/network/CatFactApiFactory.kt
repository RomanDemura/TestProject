package tech.demura.testproject.data_layer.cat_fact_api.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object CatFactApiFactory {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://catfact.ninja/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val apiService: CatFactApiService = retrofit.create()
}