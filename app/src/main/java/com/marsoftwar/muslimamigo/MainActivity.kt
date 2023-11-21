package com.marsoftwar.muslimamigo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.marsoftwar.muslimamigo.authentication.GoogleAuthUiClient
import com.marsoftwar.muslimamigo.data.repository.MainRepository
import com.marsoftwar.muslimamigo.ui.common.CustomNavBar
import com.marsoftwar.muslimamigo.ui.navigation.MainNavGraph
import com.marsoftwar.muslimamigo.ui.theme.ComposeTutoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var googleAuthUiClient: GoogleAuthUiClient

    @Inject
    lateinit var mainRepository: MainRepository

    val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scope.launch { mainRepository.mergingData() }
        setContent {
            ComposeTutoTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val navController = rememberNavController()

        Scaffold(content= {
            MainNavGraph(navController,it)
        },
            bottomBar = {
                CustomNavBar(navController = navController)
            })
    }
}

@Preview
@Composable
fun ComposePrev() {

}

