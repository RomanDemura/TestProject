package tech.demura.testproject.presentation.navigation

import android.net.Uri
import com.google.gson.Gson
import tech.demura.testproject.domain.News as domainNews

sealed class Screen(
    val route: String
) {
    object NewsList : Screen(ROUTE_NEWS_LIST)
    object News : Screen(ROUTE_NEWS) {
        private const val ROUTE_FOR_ARGS = "news"
        fun getRouteWithNews(news: domainNews): String {
            val newsJson = Gson().toJson(news)
            return "$ROUTE_FOR_ARGS/${newsJson.encode()}"
        }
    }

    fun String.encode(): String {
        return Uri.encode(this)
    }

    object MainScreen : Screen(ROUTE_MAIN_SCREEN)

    companion object {
        const val KEY_NEWS = "news"

        const val ROUTE_NEWS_LIST = "news_list"
        const val ROUTE_NEWS = "news/{$KEY_NEWS}"
        const val ROUTE_MAIN_SCREEN = "main_screen"
    }
}
