package tech.demura.testproject.ui_layer.newsListScreen

import androidx.lifecycle.LiveData
import tech.demura.testproject.domain_layer.news.entites.News

sealed class NewsListScreenState() {
    object Initial : NewsListScreenState()
    data class NewsList(
        val featuredNews: LiveData<List<News>>,
        val latestNews: LiveData<List<News>>
    ) : NewsListScreenState()
}
