package com.marsoftwar.muslimamigo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.marsoftwar.muslimamigo.MainScreen

@Composable
fun NestedNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ParentNav.Auth.route,
        route = ParentNav.ROOT.route
    ){
        AuthenticationGraph(navController){
            navController.navigate(ParentNav.MainScreens.route){
                popUpTo(ParentNav.Auth.route)
            }
        }
        composable(route = ParentNav.MainScreens.route){
            MainScreen()
        }
    }
}

sealed class ParentNav(val route:String) {
    object Auth : ParentNav(route = "Auth")
    object MainScreens : ParentNav(route = "MainScreens")
    object ROOT : ParentNav(route = "ROOT")
}