package tech.demura.testproject.ui_layer.newsListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
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

    val featuredNewsState =
        viewModel.featuredNewsState.observeAsState(NewsListFeaturedState.Initial)
    val latestNewsState =
        viewModel.latestNewsState.observeAsState(NewsListLatestState.Initial)

    val currentFeaturedNewsState = featuredNewsState.value
    val currentLatestNewsState = latestNewsState.value

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
            Text(
                text = stringResource(R.string.featured_news),
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(8.dp))

            when (currentFeaturedNewsState) {
                is NewsListFeaturedState.Initial -> {}

                is NewsListFeaturedState.Loading -> {
                    Box(modifier = Modifier.size(220.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = MaterialTheme.colors.onPrimary)
                    }
                }

                is NewsListFeaturedState.FeaturedNews -> {
                    FeaturedNews(
                        featuredNews = currentFeaturedNewsState.featuredNews,
                        nextDataIsLoading = currentFeaturedNewsState.featuredNewsIsLoading,
                        loadData = {
                            viewModel.loadNextFeaturedNews()
                        },
                        onNewsClick = {
                            viewModel.markFeaturedNews(it)
                            onNewsClick(it)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.latest_news),
                fontStyle = FontStyle.Italic,
                fontSize = 24.sp,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(8.dp))
            when (currentLatestNewsState) {
                is NewsListLatestState.Initial -> {}

                is NewsListLatestState.Loading -> {
                    Box(
                        modifier = Modifier
                            .height(96.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colors.onPrimary)
                    }
                }

                is NewsListLatestState.LatestNews -> {
                    LatestNews(
                        latestNews = currentLatestNewsState.latestNews,
                        nextDataIsLoading = currentLatestNewsState.latestNewsIsLoading,
                        loadData = {
                            viewModel.loadNextLatestdNews()
                        },
                        publishTime = { viewModel.getPublishTime(it) },
                        onNewsClick = {
                            viewModel.markLatestNews(it)
                            onNewsClick(it)
                        })
                }
            }


        }
    }
}

@Composable
fun FeaturedNews(
    featuredNews: List<News>,
    nextDataIsLoading: Boolean,
    loadData: () -> Unit,
    onNewsClick: (news: News) -> Unit
) {
    LazyRow() {
        items(
            items = featuredNews,
            key = { it.id }
        ) {
            FeaturedNewsCard(it, onClick = {
                onNewsClick(it)
            })
            Spacer(modifier = Modifier.width(16.dp))
        }
        item {
            if (nextDataIsLoading) {
                Box(
                    modifier = Modifier
                        .size(220.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colors.onPrimary)
                }
            } else {
                SideEffect {
                    loadData()
                }
            }
        }
    }
}


@Composable
fun LatestNews(
    latestNews: List<News>,
    nextDataIsLoading: Boolean,
    loadData: () -> Unit,
    publishTime: (News) -> String,
    onNewsClick: (news: News) -> Unit
) {
    LazyColumn() {
        items(
            items = latestNews,
            key = { it.id }
        ) {
            LatestNewsCard(
                publishTime = publishTime(it),
                news = it,
                onClick = { onNewsClick(it) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            if (nextDataIsLoading) {
                Box(
                    modifier = Modifier
                        .height(96.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colors.onPrimary)
                }
            } else {
                SideEffect {
                    loadData()
                }
            }
        }
    }
}




