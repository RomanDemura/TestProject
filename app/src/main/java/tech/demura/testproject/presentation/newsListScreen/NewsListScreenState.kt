package tech.demura.testproject.presentation.newsListScreen

import tech.demura.testproject.domain.News

sealed class NewsListScreenState() {
    object Initial: NewsListScreenState()
    data class NewsList(val featuredNews: List<News>, val latestNews: List<News>): NewsListScreenState()
}
