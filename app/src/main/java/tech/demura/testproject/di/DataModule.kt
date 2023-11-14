package tech.demura.testproject.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import tech.demura.testproject.data_layer.cat_fact_api.network.CatFactApiFactory
import tech.demura.testproject.data_layer.cat_fact_api.network.CatFactApiService
import tech.demura.testproject.data_layer.cat_image_api.network.CatImageApiFactory
import tech.demura.testproject.data_layer.cat_image_api.network.CatImageApiService
import tech.demura.testproject.data_layer.repository.NewsRepositoryImpl
import tech.demura.testproject.domain_layer.news.repository.NewsRepository

@Module
interface DataModule {
    @ApplicationScope
    @Binds
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideCatFactApiService(): CatFactApiService {
            return CatFactApiFactory.apiService
        }

        @ApplicationScope
        @Provides
        fun provideCatImageApiService(): CatImageApiService {
            return CatImageApiFactory.apiService
        }
    }
}