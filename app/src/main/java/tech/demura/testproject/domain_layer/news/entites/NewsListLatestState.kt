package tech.demura.testproject.domain_layer.news.entites

sealed class NewsListLatestState {

    object Initial : NewsListLatestState()

    object Loading: NewsListLatestState()

    data class LatestNews(
        val latestNews: List<News>,
        val latestNewsIsLoading: Boolean = false
    ) : NewsListLatestState()
}
