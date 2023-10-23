package tech.demura.testproject.ui_layer.newsListScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.demura.testproject.domain_layer.news.entites.News

@Composable
fun LatestNewsCard(
    news: News,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .clickable { onClick() },
        backgroundColor = if (news.isViewed){
            Color.DarkGray.copy(alpha = 0.5f)
        } else {
            Color.DarkGray
        }

    ) {
        Row(modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)) {

            Image(
                painter = painterResource(id = news.imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(96.dp)
                    .padding(end = 8.dp)
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "${news.title} ${news.isViewed}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    fontSize = 20.sp,
                    maxLines = 2,
                    fontStyle = FontStyle.Italic
                )

                Text(
                    text = "1 hour ago",
                    modifier = Modifier.padding()
                )
            }
        }
    }
}