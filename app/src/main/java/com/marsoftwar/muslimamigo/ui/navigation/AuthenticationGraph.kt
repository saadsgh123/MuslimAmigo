package com.marsoftwar.muslimamigo.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.marsoftwar.muslimamigo.authentication.GoogleAuthUiClient
import com.marsoftwar.muslimamigo.ui.loginsignup.Auth_ids
import com.marsoftwar.muslimamigo.ui.loginsignup.EntryScreen
import com.marsoftwar.muslimamigo.ui.loginsignup.LogInSignInScreen
import com.marsoftwar.muslimamigo.ui.loginsignup.SheetContentLogInScreen
import com.marsoftwar.muslimamigo.ui.loginsignup.SheetContentSignUpScreen
import com.marsoftwar.muslimamigo.viewmodels.auth.AuthViewModel

fun NavGraphBuilder.AuthenticationGraph(navController: NavHostController,googleAuthUiClient: GoogleAuthUiClient,navigateToMainScreens:()-> Unit){
    navigation(startDestination = AuthGraph.Entry.route,route= ParentNav.Auth.route){
        composable(route= AuthGraph.Entry.route){

            val authViewModel = hiltViewModel<AuthViewModel>()

            EntryScreen(authViewModel) {
                navController.navigate("LoginSignup/$it"){
                    popUpTo(AuthGraph.Entry.route)
                }
            }

        }
        composable(
            route = AuthGraph.LoginAndSignUp.route,
            arguments = listOf(navArgument("id"){
                type = NavType.StringType
            })
        ){
            val id = it.arguments?.getString("id")
            LogInSignInScreen(googleAuthUiClient = googleAuthUiClient) { vm ->
                when(id){
                   Auth_ids.SIGN_IN_ID -> SheetContentSignUpScreen(viewModel = vm,googleAuthUiClient, navigateToMainScreens = {
                      navController.navigate(ParentNav.MainScreens.route)
                   })
                   Auth_ids.LOG_IN_ID -> SheetContentLogInScreen(viewModel = vm, googleAuthUiClient = googleAuthUiClient,navigateToMainScreens)
               }
            }
        }
    }
}

sealed class AuthGraph(val route:String){
    object Entry:AuthGraph(route = "Entry")
    object LoginAndSignUp:AuthGraph(route = "LoginSignup/{id}")
}

