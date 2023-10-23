package tech.demura.testproject.domain_layer.news.repository

import androidx.lifecycle.LiveData
import tech.demura.testproject.domain_layer.news.entites.News

interface NewsRepository {

    fun addFeaturedNews(news: News)
    fun addLatestNews(news: News)

    fun getFeaturedNews(id: Int): News
    fun getLatestNews(id: Int): News

    fun markFeaturedNews(id: Int)
    fun markLatestNews(id: Int)

    fun markAllNews()

    fun getAllFeaturedNews(): LiveData<List<News>>

    fun getAllLatestNews(): LiveData<List<News>>
}
