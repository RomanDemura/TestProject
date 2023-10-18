package tech.demura.testproject.presentation.newsScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.demura.testproject.R

@Preview
@Composable
fun NewsScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
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
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = "We are processing your request...",
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
                text = "\n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec luctus eu dolor pharetra pulvinar. In condimentum erat maximus diam tempor fringilla. Morbi non lorem sit amet orci interdum mollis id id urna. Ut nec elit aliquam, finibus tortor vel, posuere ante. Maecenas scelerisque sem vel justo dignissim, ac efficitur enim facilisis. Duis faucibus varius libero at consectetur. Aenean non odio non nisl facilisis bibendum id sit amet odio. Donec in elementum felis, non eleifend lorem. Quisque turpis velit, laoreet id lobortis vel, commodo eget nulla. Ut elit velit, sagittis in finibus ac, finibus sed justo. Nulla ut diam pulvinar, laoreet dolor sit amet, tempus ipsum. Aliquam eu dictum urna. Vestibulum posuere rhoncus sapien sit amet rutrum.\n" +
                        "\n" +
                        "Curabitur in felis quis sem tincidunt placerat at sit amet tortor. Duis vulputate velit ut ante imperdiet, in accumsan arcu eleifend. Integer at erat nec tellus mollis placerat. Integer id odio feugiat, hendrerit orci eu, aliquam lectus. Sed porttitor tellus vitae sollicitudin malesuada. Vivamus est risus, auctor eu nunc sed, aliquam porta turpis. Cras fermentum, orci vitae vestibulum ultrices, elit velit porttitor urna, non iaculis lacus tortor sed odio. Etiam quis ex placerat, facilisis purus vel, congue elit. Sed interdum diam non risus suscipit pellentesque. Quisque ligula orci, molestie blandit enim vitae, euismod congue libero. Curabitur porta neque vel mi vestibulum, condimentum sollicitudin nunc ultricies. Nullam vel nisi scelerisque dolor luctus viverra vitae varius nisi. Nullam vulputate fermentum ipsum non imperdiet. Etiam dolor orci, laoreet vitae vulputate vitae, porttitor quis tellus. Fusce volutpat sed nunc eu interdum. Maecenas pulvinar, erat vel interdum lobortis, massa quam imperdiet leo, ac finibus risus nisl commodo purus.\n" +
                        "\n" +
                        "Mauris ullamcorper dictum facilisis. Mauris aliquam non ipsum venenatis dignissim. Aenean accumsan metus nec aliquet pulvinar. In blandit urna libero, id malesuada justo elementum ut. Nullam sit amet eros eget risus feugiat ultricies et sit amet sem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed hendrerit elit sed metus rutrum facilisis. Maecenas rhoncus cursus scelerisque. Sed fringilla, urna vel mollis tincidunt, neque lorem rhoncus sem, vitae aliquam lorem purus in metus. Praesent nec rutrum tellus. Donec congue bibendum tristique.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp
            )
        }
    }
}