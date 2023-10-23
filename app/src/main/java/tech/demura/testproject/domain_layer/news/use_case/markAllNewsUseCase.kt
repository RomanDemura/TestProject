package tech.demura.testproject.domain_layer.news.use_case

import tech.demura.testproject.domain_layer.news.repository.NewsRepository

class markAllNewsUseCase(private val repository: NewsRepository) {
    fun markAllNews() =
        repository.markAllNews()

}