package tech.demura.testproject.domain_layer.news.use_case

import tech.demura.testproject.domain_layer.news.repository.NewsRepository

class getFeaturedNewsUseCase (private val repository: NewsRepository){
    fun getNews(id: Int) =
        repository.getFeaturedNews(id = id)
}