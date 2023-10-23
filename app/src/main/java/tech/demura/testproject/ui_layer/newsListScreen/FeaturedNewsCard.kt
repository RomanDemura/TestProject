package tech.demura.testproject.ui_layer.newsListScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.demura.testproject.domain_layer.news.entites.News

@Composable
fun FeaturedNewsCard(
    news: News,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(320.dp)
            .clickable { onClick() },
        elevation = 8.dp,
        border = BorderStroke(1.dp, Color.DarkGray)
    ) {

        Image(
            painter = painterResource(id = news.imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = news.title,
                maxLines = 3,
                modifier = Modifier.padding(15.dp),
                fontFamily = FontFamily.Serif,
                fontSize = 36.sp,
                color = Color.White,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.DarkGray,
                        offset = Offset(3f, 13f),
                        blurRadius = 4f
                    )
                )

            )
        }
    }
}