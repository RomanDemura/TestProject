package tech.demura.testproject.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.demura.testproject.presentation.newsListScreen.FeaturedNewsCard
import tech.demura.testproject.presentation.newsListScreen.LatestNewsCard


@Preview
@Composable
fun NewsListScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {

// Featured news part
        Column() {

            Text(
                text = "Featured",
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(16.dp))

            LazyRow() {
                items(15) {
                    FeaturedNewsCard()
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

// Latest news part
        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = "Latest news",
                fontStyle = FontStyle.Italic,
                fontSize = 24.sp,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn() {
                items(30) {
                    LatestNewsCard()
                }
            }
        }
    }
}




