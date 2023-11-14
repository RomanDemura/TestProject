package tech.demura.testproject.ui_layer.newsListScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.viewmodel.compose.viewModel
import tech.demura.testproject.R
import tech.demura.testproject.domain_layer.news.entites.News
import tech.demura.testproject.domain_layer.news.entites.NewsListFeaturedState
import tech.demura.testproject.domain_layer.news.entites.NewsListLatestState
import tech.demura.testproject.ui_layer.ViewModelFactory
import kotlin.math.absoluteValue


@Composable
fun NewsListScreen(
    viewModelFactory: ViewModelFactory,
    onNewsClick: (news: News) -> Unit
) {
    val viewModel: NewsListViewModel = viewModel(factory = viewModelFactory)

    val featuredNewsState =
        viewModel.featuredNewsState.collectAsState(NewsListFeaturedState.Initial)
    val latestNewsState =
        viewModel.latestNewsState.collectAsState(NewsListLatestState.Initial)

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
            // FEATURED NEWS PART
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
                        loadData = viewModel::loadNextFeaturedNews,
                        onNewsClick = {
                            viewModel.markFeaturedNews(it)
                            onNewsClick(it)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // LATEST NEWS PART
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
                        loadData = viewModel::loadNextLatestdNews,
                        publishTime = viewModel::convertNewsPublishDateToTimeAgo,
                        onNewsClick = {
                            viewModel.markLatestNews(it)
                            onNewsClick(it)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeaturedNews(
    featuredNews: List<News>,
    nextDataIsLoading: Boolean,
    loadData: () -> Unit,
    onNewsClick: (news: News) -> Unit
) {

    val pagerState = rememberPagerState(pageCount = {
        featuredNews.size + 1
    })

    Box(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
    ) {

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(start = 64.dp, end = 64.dp),
            pageSpacing = 16.dp,
            pageSize = PageSize.Fixed(250.dp),
            modifier = Modifier.fillMaxSize(),

            ) { page ->
            val modifier = Modifier
                .size(250.dp)
                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
            if (page == featuredNews.size - 1) {
                loadData()
            }
            if (page < featuredNews.size) {
                FeaturedNewsCard(
                    news = featuredNews[page],
                    onClick = { onNewsClick(featuredNews[page]) },
                    modifier = modifier
                )
            } else {
                Box(
                    modifier = Modifier
                        .height(96.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colors.onPrimary)
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




