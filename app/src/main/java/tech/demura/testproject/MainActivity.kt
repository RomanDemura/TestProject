package tech.demura.testproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import tech.demura.testproject.ui.theme.TestProjectTheme
import tech.demura.testproject.ui_layer.NewsApplication
import tech.demura.testproject.ui_layer.ViewModelFactory
import tech.demura.testproject.ui_layer.mainScreen.MainScreen
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as NewsApplication).applicationComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            TestProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(viewModelFactory = viewModelFactory)
                }
            }
        }
    }
}

