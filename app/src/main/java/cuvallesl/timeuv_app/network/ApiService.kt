package cuvallesl.timeuv_app.network

import cuvallesl.timeuv_app.models.LoginRequest
import cuvallesl.timeuv_app.models.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}