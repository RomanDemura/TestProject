package tech.demura.testproject.ui_layer.newsScreen

import androidx.lifecycle.ViewModel
import tech.demura.testproject.di.idQualifier
import tech.demura.testproject.domain_layer.news.entites.News
import tech.demura.testproject.domain_layer.news.use_case.GetNewsByIdUseCase
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    @idQualifier private val newsId: Int,
    private val getNewsByIdUseCase: GetNewsByIdUseCase
) : ViewModel() {

    fun getNews(): News {
        return getNewsByIdUseCase(newsId)
    }
}