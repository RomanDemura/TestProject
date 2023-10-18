package tech.demura.testproject.presentation.newsListScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.demura.testproject.R

@Composable
fun FeaturedNewsCard() {
    Card(
        modifier = Modifier
            .size(320.dp)
            .padding(end = 16.dp),
        elevation = 8.dp,
        border = BorderStroke(1.dp, Color.DarkGray)
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "We are processing your request...",
                maxLines = 3,
                modifier = Modifier.padding(15.dp),
                fontFamily = FontFamily.Cursive,
                fontSize = 36.sp
            )
        }
    }
}