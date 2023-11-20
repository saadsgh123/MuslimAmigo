package com.marsoftwar.muslimamigo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marsoftwar.muslimamigo.authentication.GoogleAuthUiClient

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

        }
    }
}

sealed class ParentNav(val route:String) {
    object Auth : ParentNav(route = "Auth")
    object MainScreens : ParentNav(route = "MainScreens")
    object ROOT : ParentNav(route = "ROOT")
}