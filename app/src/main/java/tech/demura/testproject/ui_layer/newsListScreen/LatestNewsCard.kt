package tech.demura.testproject.ui_layer.newsListScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.demura.testproject.domain_layer.news.entites.News

@Composable
fun LatestNewsCard(
    news: News,
    publishTime: String,
    onClick: () -> Unit
) {
    val backgroundColor =
        if (news.isViewed) {
            MaterialTheme.colors.surface
        } else {
            MaterialTheme.colors.secondary
        }
    Card(
        modifier = Modifier
            .height(96.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        backgroundColor = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp, bottom = 12.dp)
        ) {

            Image(
                painter = painterResource(id = news.imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(96.dp)
                    .padding(end = 8.dp, start = 8.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = news.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    color = MaterialTheme.colors.onSecondary,
                    fontSize = 20.sp,
                    maxLines = 2,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = publishTime,
                    modifier = Modifier.padding(),
                    color = MaterialTheme.colors.onSecondary
                )
            }
        }
    }
}

