package com.example.exam_android.network

import com.example.exam_android.data.models.CategoriesResponse
import com.example.exam_android.data.models.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("v3/b/6760342bacd3cb34a8ba8657")
    suspend fun getCategories(): Response<CategoriesResponse>

    @GET
    suspend fun getProducts(@Url url: String): Response<ProductsResponse>
}

// Configuration Retrofit moderne
object NetworkModule {

    private const val BASE_URL = "https://api.jsonbin.io/"

    // Création d'OkHttpClient avec logging (optionnel)
    private val okHttpClient = okhttp3.OkHttpClient.Builder()
        .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
        .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
        .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
        .build()

    private val retrofit = retrofit2.Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}

// Extension pour gérer les erreurs de manière moderne
suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): com.example.exam_android.data.models.ApiResult<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                com.example.exam_android.data.models.ApiResult.Success(it)
            } ?: com.example.exam_android.data.models.ApiResult.Error("Réponse vide")
        } else {
            com.example.exam_android.data.models.ApiResult.Error("Erreur ${response.code()}: ${response.message()}")
        }
    } catch (e: java.net.UnknownHostException) {
        com.example.exam_android.data.models.ApiResult.Error("Pas de connexion internet")
    } catch (e: java.net.SocketTimeoutException) {
        com.example.exam_android.data.models.ApiResult.Error("Délai d'attente dépassé")
    } catch (e: Exception) {
        com.example.exam_android.data.models.ApiResult.Error("Erreur: ${e.localizedMessage}")
    }
}