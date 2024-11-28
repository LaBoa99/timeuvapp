package cuvallesl.timeuv_app.network

import cuvallesl.timeuv_app.network.ApiService
import cuvallesl.timeuv_app.network.interceptors.AuthInterceptor
import cuvallesl.timeuv_app.network.token.TokenStore
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

object ApiClient {
    private const val BASE_URL = "http://192.168.100.7:3000/"

    private val tokenStore = TokenStore
    private val authInterceptor = AuthInterceptor { tokenStore.getToken() }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)  // Añadimos el OkHttpClient al Retrofit
            .build()
            .create(ApiService::class.java)
    }
}