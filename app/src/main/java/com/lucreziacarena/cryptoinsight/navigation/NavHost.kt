package com.lucreziacarena.cryptoinsight.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.showtimecompose.navigation.NavigationItem
import com.lucreziacarena.cryptoinsight.feature.home.composable.Home

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Home.route) {
            Home()
            //ShowListScreen(navController=navController)
        }
        composable(
            route = "${NavigationItem.Detail.route}?id={showId}",
            arguments = listOf(
                navArgument("showId") {
                    type = NavType.StringType
                    nullable = false
                },
            )
        ) { backStackEntry ->
           // backStackEntry.arguments?.getString("showId")?.let { ShowDetailScreen(it) }
        }

    }
}
