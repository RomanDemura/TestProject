package tech.demura.testproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import tech.demura.testproject.presentation.mainScreen.MainScreen
import tech.demura.testproject.presentation.mainScreen.MainScreenViewModel
import tech.demura.testproject.presentation.navigation.AppNavGraph
import tech.demura.testproject.presentation.navigation.rememberNavigationState
import tech.demura.testproject.ui.theme.TestProjectTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainScreenViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navigationState = rememberNavigationState()
                    AppNavGraph(
                        navHostController = navigationState.navHostController,
                        MainScreenContent = { MainScreen(viewModel) }
                    )
//                    MainScreen()
                }
            }
        }
    }
}

