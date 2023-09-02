package com.marsoftwar.muslimamigo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.marsoftwar.muslimamigo.authentication.GoogleAuthUiClient
import com.marsoftwar.muslimamigo.ui.common.CustomNavBar
import com.marsoftwar.muslimamigo.ui.home.CustomTopAppBar
import com.marsoftwar.muslimamigo.ui.navigation.MainNavGraph
import com.marsoftwar.muslimamigo.ui.navigation.NestedNavGraph
import com.marsoftwar.muslimamigo.ui.navigation.ParentNav
import com.marsoftwar.muslimamigo.ui.theme.ComposeTutoTheme
import com.marsoftwar.muslimamigo.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var googleAuthUiClient: GoogleAuthUiClient
    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTutoTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NestedNavGraph(
                    navController = navController,
                    googleAuthUiClient = googleAuthUiClient,
                    startDestination = if (auth.currentUser != null) ParentNav.MainScreens.route else ParentNav.Auth.route
                )
            }
        }
    }
}

@Composable
fun MainScreen(googleAuthUiClient: GoogleAuthUiClient) {
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
            },
            topBar = {
                CustomTopAppBar(size = 200.dp, googleAuthUiClient = googleAuthUiClient)
            })
    }
}

@Preview
@Composable
fun ComposePrev() {

}

