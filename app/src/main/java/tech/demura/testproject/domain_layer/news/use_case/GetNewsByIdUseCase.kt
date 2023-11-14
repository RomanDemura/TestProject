package tech.demura.testproject.domain_layer.news.use_case

import tech.demura.testproject.domain_layer.news.entites.News
import tech.demura.testproject.domain_layer.news.repository.NewsRepository
import javax.inject.Inject

class GetNewsByIdUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(newsId: Int): News {
        return repository.getNewsById(newsId)
    }
}