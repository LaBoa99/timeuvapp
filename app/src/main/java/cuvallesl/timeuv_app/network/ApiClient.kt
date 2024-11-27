package cuvallesl.timeuv_app.network

import cuvallesl.timeuv_app.network.ApiService
import cuvallesl.timeuv_app.network.interceptors.AuthInterceptor
import cuvallesl.timeuv_app.network.token.TokenStore
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

object ApiClient {
    private const val BASE_URL = "http://208.167.249.168:3000/"
    private const val tokenStore = TokenStore

    val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor())

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client()
            .build()
            .create(ApiService::class.java)
    }
}