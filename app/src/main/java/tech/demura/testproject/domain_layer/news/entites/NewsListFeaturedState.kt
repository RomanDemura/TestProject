package tech.demura.testproject.domain_layer.news.entites

sealed class NewsListFeaturedState  {
    object Initial : NewsListFeaturedState()
    object Loading: NewsListFeaturedState()
    data class FeaturedNews(
        val featuredNews: List<News>,
        val featuredNewsIsLoading: Boolean = false,
    ) : NewsListFeaturedState()
}
