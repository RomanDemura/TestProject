package tech.demura.testproject.presentation.navigation

sealed class Screen(
    val route: String
){
    object NewsList : Screen(ROUTE_NEWS_LIST)
    object News : Screen(ROUTE_NEWS)
    object MainScreen: Screen(ROUTE_MAIN_SCREEN)

    private companion object{
        const val ROUTE_NEWS_LIST = "news_list"
        const val ROUTE_NEWS = "news"
        const val ROUTE_MAIN_SCREEN = "main_screen"
    }
}
