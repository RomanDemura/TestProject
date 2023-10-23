package tech.demura.testproject.domain_layer.news.use_case

import tech.demura.testproject.domain_layer.news.repository.NewsRepository

class getLatestNewsUseCase (private val repository: NewsRepository){
    fun getNews(id: Int) =
        repository.getLatestNews(id = id)
}