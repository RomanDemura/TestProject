package tech.demura.testproject.ui_layer.newsListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import tech.demura.testproject.domain_layer.news.entites.News


@Composable
fun NewsListScreen(
    onNewsClick: (news: News) -> Unit
) {
    val viewModel: NewsListViewModel = viewModel()
    val screenState = viewModel.screenState.observeAsState(NewsListScreenState.Initial)
    val currentState = screenState.value

    viewModel.getNews()
    if (currentState !is NewsListScreenState.NewsList) return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        FeaturedNews(featuredNews = currentState.featuredNews, onNewsClick = onNewsClick)
        Spacer(modifier = Modifier.height(16.dp))
        LatestNews(latestNews = currentState.latestNews, onNewsClick = onNewsClick)
    }
}

@Composable
fun FeaturedNews(
    featuredNews: List<News>,
    onNewsClick: (news: News) -> Unit
) {
    Column() {
        Text(
            text = "Featured",
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow() {
            items(featuredNews.size) {
                val news = featuredNews[it]
                FeaturedNewsCard(news, onClick = { onNewsClick(news) })
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
fun LatestNews(
    latestNews: List<News>,
    onNewsClick: (news: News) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = "Latest news",
            fontStyle = FontStyle.Italic,
            fontSize = 24.sp,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn() {
            items(latestNews.size) {
                val news = latestNews[it]
                LatestNewsCard(news, onClick = { onNewsClick(news) })
            }
        }
    }
}




