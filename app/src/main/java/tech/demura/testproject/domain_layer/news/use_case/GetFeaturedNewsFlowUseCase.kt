package tech.demura.testproject.domain_layer.news.use_case

import tech.demura.testproject.domain_layer.news.repository.NewsRepository
import javax.inject.Inject

class GetFeaturedNewsFlowUseCase @Inject constructor(private val repository: NewsRepository) {
    operator fun invoke() = repository.getFeaturedNewsFlow()
}