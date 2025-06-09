// viewmodel/CategoriesViewModel.kt
package com.example.exam_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exam_android.data.models.*
import com.example.exam_android.repository.ProductRepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {

    private val repository = ProductRepositoryProvider.repository

    private val _uiState = MutableStateFlow(CategoriesUiState())
    val uiState: StateFlow<CategoriesUiState> = _uiState.asStateFlow()

    init {
        loadCategories()
    }

    fun loadCategories() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            when (val result = repository.getCategories()) {
                is ApiResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        categories = result.data,
                        isLoading = false,
                        errorMessage = null
                    )
                }
                is ApiResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
                is ApiResult.Loading -> {
                    // Déjà géré ci-dessus
                }
            }
        }
    }

    fun retry() {
        loadCategories()
    }
}

// viewmodel/ProductsViewModel.kt
class ProductsViewModel : ViewModel() {

    private val repository = ProductRepositoryProvider.repository

    private val _uiState = MutableStateFlow(ProductsUiState())
    val uiState: StateFlow<ProductsUiState> = _uiState.asStateFlow()

    fun loadProducts(category: Category) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null,
                categoryTitle = category.title
            )

            when (val result = repository.getProducts(category.products_url)) {
                is ApiResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        products = result.data,
                        isLoading = false,
                        errorMessage = null
                    )
                }
                is ApiResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
                is ApiResult.Loading -> {
                    // Déjà géré
                }
            }
        }
    }

    fun retry(category: Category) {
        loadProducts(category)
    }
}