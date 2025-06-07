package com.example.exam_android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exam_android.screens.HomeScreen
import com.example.exam_android.screens.ProductScreen
import com.example.exam_android.screens.ProfilScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable("Home") {
            HomeScreen(navController)
        }
        composable("Profile"){
            ProfilScreen(navController)
        }
        composable("Products") {
            ProductScreen(navController)
        }
    }
}