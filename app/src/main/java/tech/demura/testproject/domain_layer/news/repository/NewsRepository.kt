package tech.demura.testproject.domain_layer.news.repository

import androidx.lifecycle.LiveData
import tech.demura.testproject.domain_layer.news.entites.News

interface NewsRepository {

    fun addNews(news: News, key: String)

    fun getNews(id: Int): News

    fun getFeaturedNews(): LiveData<List<News>>

    fun getLatestNews(): LiveData<List<News>>
}