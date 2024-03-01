package com.lucreziacarena.cryptoinsight.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lucreziacarena.cryptoinsight.feature.detail.composable.DetailScreen
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
            Home(navController=navController)
        }
        composable(
            route = "${NavigationItem.Detail.route}?id={cryptoId}&name={name}",
            arguments = listOf(
                navArgument("cryptoId") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("name") {
                    type = NavType.StringType
                    nullable = false
                },
            )
        ) { backStackEntry ->
            val id= backStackEntry.arguments?.getString("cryptoId")
            val name= backStackEntry.arguments?.getString("name")
            DetailScreen(cryptoId = id!!, name=name?:"", navController = navController)
        }

    }
}
