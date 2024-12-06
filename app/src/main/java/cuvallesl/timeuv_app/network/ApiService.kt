package cuvallesl.timeuv_app.network

import cuvallesl.timeuv_app.models.Course
import cuvallesl.timeuv_app.models.LoginRequest
import cuvallesl.timeuv_app.models.LoginResponse
import cuvallesl.timeuv_app.models.Schedule
import cuvallesl.timeuv_app.models.UserCreateRequest
import cuvallesl.timeuv_app.models.User
import retrofit2.Call
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    // USER
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("users")
    fun createUser(@Body request: UserCreateRequest): Call<User>

    // COURSES
    @GET("courses")
    suspend fun getCourses(): Response<List<Course>>

    // SCHEDULE
    @PATCH("schedules/self/enroll/{course_id}")
    fun enroll(@Path("course_id") courseId: String): Call<Schedule>
}