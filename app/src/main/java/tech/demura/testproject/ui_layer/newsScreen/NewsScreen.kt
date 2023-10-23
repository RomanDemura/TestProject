package tech.demura.testproject.ui_layer.newsScreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.demura.testproject.R
import tech.demura.testproject.domain_layer.news.entites.News

@Composable
fun NewsScreen(
    news: News,
    onBackPressed: () -> Unit
) {
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
                }
            )
        }
    ) { paddingValues ->
        News(
            news = news,
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
                elevation = 8.dp,
                border = BorderStroke(1.dp, Color.DarkGray)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = news.imageId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = news.title,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        maxLines = 3,
                        fontSize = 32.sp,
                        fontStyle = FontStyle.Italic
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
