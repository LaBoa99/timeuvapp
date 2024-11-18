package cuvallesl.timeuv_app.fcm

import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.messaging.FirebaseMessaging

fun saveTokenToPreferences(context: Context, token: String) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("fcm_preferences", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("fcm_token", token)
    editor.apply()
}

fun genAndSaveToken(context: Context) {
    FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
        if (!task.isSuccessful) {
            println("El token no fue generado")
            return@addOnCompleteListener
        }

        val token = task.result
        println("El token es: $token")
        saveTokenToPreferences(context, token)
    }
}

fun getTokenFromPreferences(context: Context): String? {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("fcm_preferences", Context.MODE_PRIVATE)
    return sharedPreferences.getString("fcm_token", null) // Devuelve null si no existe
}

