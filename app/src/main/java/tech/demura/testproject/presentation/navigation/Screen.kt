package tech.demura.testproject.presentation.navigation

sealed class Screen(
    val route: String
){
    object NewsList : Screen(ROUTE_NEWS_LIST)
    object News : Screen(ROUTE_NEWS)

    private companion object{
        const val ROUTE_NEWS_LIST = "news_list"
        const val ROUTE_NEWS = "news"
    }
}
