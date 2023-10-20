package tech.demura.testproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tech.demura.testproject.domain.News

class NavigationState(
    val navHostController: NavHostController
) {
    fun navigateTo(route: String){
        navHostController.navigate(route = route){
            popUpTo(navHostController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
        }
    }
    fun navigateToNews(news: News){
        navHostController.navigate(Screen.News.getRouteWithNews(news))
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState{
    return remember {
        NavigationState(navHostController)
    }
}