package cuvallesl.timeuv_app.network.token


import android.content.Context

object TokenStore {

    private const val PREFS_NAME = "app_prefs"
    private const val TOKEN_KEY = "access_token"

    private lateinit var sharedPreferences: android.content.SharedPreferences

    fun initialize(context: Context) {
        sharedPreferences = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(token: String) {
        if (!::sharedPreferences.isInitialized) {
            throw IllegalStateException("TokenStore no ha sido inicializado. Llama a initialize(context).")
        }
        sharedPreferences.edit()
            .putString(TOKEN_KEY, token)
            .apply()
    }

    fun getToken(): String  {
        return sharedPreferences.getString(TOKEN_KEY, "").toString()
    }

    fun clearToken() {
        if (!::sharedPreferences.isInitialized) {
            throw IllegalStateException("TokenStore no ha sido inicializado. Llama a initialize(context).")
        }
        sharedPreferences.edit()
            .remove(TOKEN_KEY)
            .apply()
    }
}
