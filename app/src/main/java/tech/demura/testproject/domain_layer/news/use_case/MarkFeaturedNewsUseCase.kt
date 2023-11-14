package tech.demura.testproject.domain_layer.news.use_case

import tech.demura.testproject.domain_layer.news.entites.News
import tech.demura.testproject.domain_layer.news.repository.NewsRepository
import javax.inject.Inject

class MarkFeaturedNewsUseCase @Inject constructor(private val repository: NewsRepository) {
    suspend operator fun invoke(news: News) = repository.markFeaturedNews(news)
}