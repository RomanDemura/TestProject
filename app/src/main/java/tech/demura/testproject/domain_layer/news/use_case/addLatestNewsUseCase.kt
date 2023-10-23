package tech.demura.testproject.domain_layer.news.use_case

import tech.demura.testproject.domain_layer.news.entites.News
import tech.demura.testproject.domain_layer.news.repository.NewsRepository

class addLatestNewsUseCase(private val repository: NewsRepository) {
    fun addNews(news: News) =
        repository.addLatestNews(news = news)
}