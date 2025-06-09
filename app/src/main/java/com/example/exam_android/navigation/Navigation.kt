package com.example.exam_android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.exam_android.screens.CategoriesScreen
import com.example.exam_android.screens.HomeScreen
import com.example.exam_android.screens.ProductDetailScreen
import com.example.exam_android.screens.ProductListScreen
import com.example.exam_android.screens.ProfilScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("profile") {
            ProfilScreen(navController)
        }
        composable("products") {
            CategoriesScreen(navController)
        }

        // Liste des produits d'une catégorie
        composable(
            route = "product_list/{categoryId}/{categoryTitle}/{productsUrl}",
            arguments = listOf(
                navArgument("categoryId") { type = NavType.StringType },
                navArgument("categoryTitle") { type = NavType.StringType },
                navArgument("productsUrl") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
            val categoryTitle = backStackEntry.arguments?.getString("categoryTitle") ?: ""
            val productsUrl = backStackEntry.arguments?.getString("productsUrl") ?: ""

            ProductListScreen(
                navController = navController,
                categoryId = categoryId,
                categoryTitle = categoryTitle,
                productsUrl = URLDecoder.decode(productsUrl, StandardCharsets.UTF_8.toString())
            )
        }

        // Détails d'un produit
        composable(
            route = "product_detail/{productName}/{productDescription}/{productImageUrl}",
            arguments = listOf(
                navArgument("productName") { type = NavType.StringType },
                navArgument("productDescription") { type = NavType.StringType },
                navArgument("productImageUrl") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val productName = backStackEntry.arguments?.getString("productName") ?: ""
            val productDescription = backStackEntry.arguments?.getString("productDescription") ?: ""
            val productImageUrl = backStackEntry.arguments?.getString("productImageUrl") ?: ""

            ProductDetailScreen(
                navController = navController,
                productName = productName,
                productDescription = productDescription,
                productImageUrl = productImageUrl
            )
        }
    }
}