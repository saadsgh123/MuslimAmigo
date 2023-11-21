package com.marsoftwar.muslimamigo.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.marsoftwar.muslimamigo.ui.account.AccountScreen
import com.marsoftwar.muslimamigo.ui.common.Screen
import com.marsoftwar.muslimamigo.ui.home.HomeScreen
import com.marsoftwar.muslimamigo.ui.video.VideoScreen
import com.marsoftwar.muslimamigo.ui.wishlist.WishListScreen
import com.marsoftwar.muslimamigo.viewmodels.content.HomeViewModel

@Composable
fun MainNavGraph(navController: NavHostController,paddingValues: PaddingValues) {

    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(Screen.Home.route){
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(homeViewModel,paddingValues)
        }
        composable(Screen.Videos.route){
            VideoScreen(paddingValues)
        }
        composable(Screen.WishList.route){
            WishListScreen()
        }
        composable(Screen.Account.route){
            AccountScreen()
        }
    }
}