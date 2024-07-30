package com.example.projekatmacke.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projekatmacke.cats.lista.cats
import com.example.projekatmacke.cats.pojedinacno.oneCat

@Composable
fun CatNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "cat",
    ){
        cats(
            route = "cat",
            onUserClick = {
                navController.navigate(route = "cat/$it")
            }
        )

        oneCat(
            route = "cat/{catId}",
            arguments = listOf(
                navArgument(name = "catId") {
                    nullable = false
                    type = NavType.StringType
                }
            ),
            onClose = {
                navController.navigateUp()
            })
    }
}