package tech.demura.testproject.domain_layer.news.use_case

import androidx.lifecycle.LiveData
import tech.demura.testproject.domain_layer.news.entites.News
import tech.demura.testproject.domain_layer.news.repository.NewsRepository

class getFeaturedNewsUseCase(private val repository: NewsRepository) {
    fun getFeaturedNews(): LiveData<List<News>> = repository.getFeaturedNews()
}