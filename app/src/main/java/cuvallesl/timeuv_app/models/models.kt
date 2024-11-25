package cuvallesl.timeuv_app.models

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val access_token: String) // login