package tech.demura.testproject.ui_layer.newsScreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import tech.demura.testproject.R
import tech.demura.testproject.domain_layer.news.entites.News
import tech.demura.testproject.ui_layer.NewsApplication

@Composable
fun NewsScreen(
    newsId: Int,
    onBackPressed: () -> Unit
) {
    val component = (LocalContext.current.applicationContext as NewsApplication)
        .applicationComponent
        .getNewsScreenComponentFactory()
        .create(newsId)

    val viewModel: NewsViewModel = viewModel(factory = component.getViewModelFactory())

    BackHandler {
        onBackPressed()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.news))
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) { paddingValues ->
        News(
            news = viewModel.getNews(),
            paddingValues = paddingValues
        )
    }
}

@Composable
fun News(news: News, paddingValues: PaddingValues) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

// Image part
        item {
            Card(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .fillParentMaxHeight(0.5f)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
                elevation = 4.dp
            ) {
                if (news.imageUrl.isNotEmpty()) {
                    AsyncImage(
                        model = news.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = news.imageId),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = news.title,
                        maxLines = 3,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        fontFamily = FontFamily.Serif,
                        fontSize = 32.sp,
                        color = Color.White,
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(0f, 0f),
                                blurRadius = 12f
                            )
                        )
                    )
                }
            }
        }

// Text part
        item {
            Text(
                text = news.text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp
            )
        }
    }
}
