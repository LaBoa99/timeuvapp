package cuvallesl.timeuv_app.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val access_token: String) // login

data class User(
    val id: Int,
    val email: String,
    val notification_enabled: Boolean
)
data class UserCreateRequest(
    val email: String,
    val password: String,
    val fcm: String?
)

data class Schedule (
    val id: Int,
    val title: String,
    @SerializedName("created_at")
    val createdAt: String,
    val enrollments: List<Course>
)

data class Course (
    val id: Long,
    val classroom: String,

    @SerializedName("course_key")
    val courseKey: String,
    @SerializedName("course_crn")
    val courseCrn: String,

    val department: String,
    val day: Long,
    val group: String,

    @SerializedName("start_time")
    val startTime: Long,
    @SerializedName("end_time")
    val endTime: Long,

    val subject: String,
    val professor: String,
    val program: String
)


