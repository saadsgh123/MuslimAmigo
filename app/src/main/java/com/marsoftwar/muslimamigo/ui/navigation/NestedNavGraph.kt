package com.marsoftwar.muslimamigo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.marsoftwar.muslimamigo.MainScreen
import com.marsoftwar.muslimamigo.authentication.GoogleAuthUiClient
import com.marsoftwar.muslimamigo.viewmodels.AuthViewModel

@Composable
fun NestedNavGraph(
    navController: NavHostController,
    googleAuthUiClient: GoogleAuthUiClient,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        route = ParentNav.ROOT.route
    ){

        AuthenticationGraph(navController, googleAuthUiClient = googleAuthUiClient){
            navController.navigate(ParentNav.MainScreens.route){
                popUpTo(ParentNav.Auth.route)
            }
        }
        composable(route = ParentNav.MainScreens.route){
            MainScreen(googleAuthUiClient){
                navController.navigate(ParentNav.ROOT.route)
            }
        }
    }
}

sealed class ParentNav(val route:String) {
    object Auth : ParentNav(route = "Auth")
    object MainScreens : ParentNav(route = "MainScreens")
    object ROOT : ParentNav(route = "ROOT")
}