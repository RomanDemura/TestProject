package tech.demura.testproject.ui_layer.newsListScreen

import tech.demura.testproject.domain_layer.news.entites.News

sealed class NewsListLatestState() {

    object Initial : NewsListLatestState()

    object Loading: NewsListLatestState()

    data class LatestNews(
        val latestNews: List<News>,
        val latestNewsIsLoading: Boolean = false
    ) : NewsListLatestState()
}
