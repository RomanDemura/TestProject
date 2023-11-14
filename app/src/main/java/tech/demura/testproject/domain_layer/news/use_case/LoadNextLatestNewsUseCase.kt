package tech.demura.testproject.domain_layer.news.use_case

import tech.demura.testproject.domain_layer.news.repository.NewsRepository
import javax.inject.Inject

class LoadNextLatestNewsUseCase @Inject constructor(private val repository: NewsRepository) {
    suspend operator fun invoke() = repository.loadNextLatestNews()
}