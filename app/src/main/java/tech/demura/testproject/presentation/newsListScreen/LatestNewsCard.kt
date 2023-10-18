package tech.demura.testproject.presentation.newsListScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.demura.testproject.R

@Composable
fun LatestNewsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
    ) {
        Row(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
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
                    text = "We are processing your request...",
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