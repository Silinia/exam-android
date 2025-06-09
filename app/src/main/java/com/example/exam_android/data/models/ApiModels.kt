// data/models/ApiModels.kt
package com.example.exam_android.data.models

// Modèles pour les catégories/rayons
data class CategoriesResponse(
    val record: List<Category>
)

data class Category(
    val category_id: String,
    val title: String,
    val products_url: String
)

// Modèles pour les produits
data class ProductsResponse(
    val record: List<Product>
)

data class Product(
    val name: String,
    val description: String,
    val picture_url: String
)

// États pour l'UI
sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
}

// États spécifiques pour l'UI
data class CategoriesUiState(
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

data class ProductsUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val categoryTitle: String = ""
)

data class ProductDetailUiState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)