package tech.demura.testproject.ui_layer.newsListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import tech.demura.testproject.R
import tech.demura.testproject.domain_layer.news.entites.News


@Composable
fun NewsListScreen(
    onNewsClick: (news: News) -> Unit
) {
    val viewModel: NewsListViewModel = viewModel()
    val screenState = viewModel.screenState.observeAsState(NewsListScreenState.Initial)
    val currentState = screenState.value

    LaunchedEffect(key1 = true){
        viewModel.getNews()
    }
    if (currentState !is NewsListScreenState.NewsList) return

    val featuredNews = viewModel.featuredNewsLD.observeAsState(listOf())
    val latestNews = viewModel.latestNewsLD.observeAsState(listOf())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.news_list))
                },
                navigationIcon = {
                    IconButton(onClick = {}, enabled = false) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                },
                actions = {
                    TextButton(onClick = {
                        viewModel.markAllNews()
                    }) {
                        Text(
                            text = stringResource(R.string.mark_all_read),
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            FeaturedNews(featuredNews = featuredNews.value, onNewsClick = {
                viewModel.markFeaturedNews(it)
                onNewsClick(it)
            })
            Spacer(modifier = Modifier.height(16.dp))
            LatestNews(
                latestNews = latestNews.value,
                publishTime = { viewModel.getPublishTime(it) },
                onNewsClick = {
                    viewModel.markLatestNews(it)
                    onNewsClick(it)
                })
        }
    }
}

@Composable
fun FeaturedNews(
    featuredNews: List<News>,
    onNewsClick: (news: News) -> Unit
) {
    Column() {
        Text(
            text = stringResource(R.string.featured_news),
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow() {
            items(featuredNews.size) {
                val news = featuredNews[it]
                FeaturedNewsCard(news, onClick = {

                    onNewsClick(news)
                })
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
fun LatestNews(
    latestNews: List<News>,
    publishTime: (News) -> String,
    onNewsClick: (news: News) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = stringResource(R.string.latest_news),
            fontStyle = FontStyle.Italic,
            fontSize = 24.sp,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn() {
            items(latestNews.size) {
                val news = latestNews[it]
                LatestNewsCard(
                    publishTime = publishTime(news),
                    news = news,
                    onClick = { onNewsClick(news) })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}




