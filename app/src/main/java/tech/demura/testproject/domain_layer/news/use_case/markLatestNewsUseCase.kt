package tech.demura.testproject.domain_layer.news.use_case

import tech.demura.testproject.domain_layer.news.repository.NewsRepository

class markLatestNewsUseCase(private val repository: NewsRepository) {
    fun markNews(id: Int) =
        repository.markLatestNews(id)

}