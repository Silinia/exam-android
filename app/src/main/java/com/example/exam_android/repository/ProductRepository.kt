package com.example.exam_android.repository

import com.example.exam_android.data.models.*
import com.example.exam_android.network.NetworkModule
import com.example.exam_android.network.safeApiCall

class ProductRepository {

    private val apiService = NetworkModule.apiService

    suspend fun getCategories(): ApiResult<List<Category>> {
        return when (val result = safeApiCall { apiService.getCategories() }) {
            is ApiResult.Success -> ApiResult.Success(result.data.record)
            is ApiResult.Error -> result
            is ApiResult.Loading -> result
        }
    }

    suspend fun getProducts(productsUrl: String): ApiResult<List<Product>> {
        return when (val result = safeApiCall { apiService.getProducts(productsUrl) }) {
            is ApiResult.Success -> ApiResult.Success(result.data.record)
            is ApiResult.Error -> result
            is ApiResult.Loading -> result
        }
    }
}

object ProductRepositoryProvider {
    val repository = ProductRepository()
}