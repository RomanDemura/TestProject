package tech.demura.testproject.domain_layer.news.repository


import kotlinx.coroutines.flow.StateFlow
import tech.demura.testproject.domain_layer.news.entites.News

interface NewsRepository {
    fun getFeaturedNewsFlow(): StateFlow<List<News>>
    fun getLatestNewsFlow(): StateFlow<List<News>>
    fun getNewsById(id: Int): News
    suspend fun loadNextFeaturedNews()
    suspend fun loadNextLatestNews()
    suspend fun markFeaturedNews(news: News)
    suspend fun markLatestNews(news: News)
    suspend fun markAllNews()
}
