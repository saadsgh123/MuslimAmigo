package com.marsoftwar.muslimamigo.ui.common

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun CustomNavBar(navController: NavHostController){
    BottomAppBar(
        containerColor = Color.Cyan,
        modifier = Modifier.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    ) {

      val navBackStackEntry by navController.currentBackStackEntryAsState()
      val currentDestination = navBackStackEntry?.destination

       screensList.forEach { screen ->
           val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            NavigationBarItem(
                selected = selected,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Cyan
                ),
                onClick = {
                  navController.navigate(screen.route){
                      popUpTo(Screen.Home.route){
                          saveState = true
                      }
                      launchSingleTop = true
                  }
                },
                icon = {
                    Icon(
                        imageVector = if (selected) screen.selected else screen.unselected,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                }
            )
        }
    }
}

val screensList = listOf(
    Screen.Home,
    Screen.Videos,
    Screen.WishList,
    Screen.Account
)

sealed class Screen(val route: String, val selected: ImageVector, val unselected: ImageVector){
    object Home: Screen("home",Icons.Filled.Home,Icons.Outlined.Home)
    object Videos: Screen("Videos",Icons.Filled.PlayCircleFilled,Icons.Outlined.PlayCircle)
    object WishList: Screen("Wishlist",Icons.Filled.Favorite,Icons.Outlined.FavoriteBorder)
    object Account: Screen("Account",Icons.Filled.AccountCircle,Icons.Outlined.AccountCircle)
}

