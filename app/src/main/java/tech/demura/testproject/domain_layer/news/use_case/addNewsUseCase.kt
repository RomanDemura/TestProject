package tech.demura.testproject.domain_layer.news.use_case

import tech.demura.testproject.domain_layer.news.entites.News
import tech.demura.testproject.domain_layer.news.repository.NewsRepository

class addNewsUseCase(private val repository: NewsRepository) {
    fun addNews(news: News, key: String){
        repository.addNews(news = news, key = key)
    }
}