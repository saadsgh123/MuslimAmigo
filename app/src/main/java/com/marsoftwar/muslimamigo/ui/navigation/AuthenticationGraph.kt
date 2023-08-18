package com.marsoftwar.muslimamigo.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.firebase.auth.FirebaseAuth
import com.marsoftwar.muslimamigo.ui.loginsignup.Auth_ids
import com.marsoftwar.muslimamigo.ui.loginsignup.EntryScreen
import com.marsoftwar.muslimamigo.ui.loginsignup.LogInSignInScreen
import com.marsoftwar.muslimamigo.ui.loginsignup.SheetContentLogInScreen
import com.marsoftwar.muslimamigo.ui.loginsignup.SheetContentSignUpScreen

fun NavGraphBuilder.AuthenticationGraph(navController: NavHostController,navigateToMainScreens:()-> Unit){
    navigation(startDestination = AuthGraph.Entry.route,route= ParentNav.Auth.route){
        composable(route= AuthGraph.Entry.route){
            EntryScreen {
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
            LogInSignInScreen(id = id!!) { vm ->
                when(id){
                   Auth_ids.SIGN_IN_ID -> SheetContentSignUpScreen(viewModel = vm,navigateToMainScreens, navigateToLogInScreens = {
                      navController.navigate(AuthGraph.Entry.route)
                   })
                   Auth_ids.LOG_IN_ID -> SheetContentLogInScreen(viewModel = vm,navigateToMainScreens)
               }
            }
        }
    }
}

sealed class AuthGraph(val route:String){
    object Entry:AuthGraph(route = "Entry")
    object LoginAndSignUp:AuthGraph(route = "LoginSignup/{id}")
}

