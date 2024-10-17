package com.api.gesco.service.config

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.api.gesco.R

class SessionManager(context: Context) {

    private var prefs : SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object{
        const val USER_TOKEN = "user_token"
    }

    fun saveAuthToken(token: String){
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        val token = prefs.getString(USER_TOKEN, null)
        Log.d("AuthToken", "Fetched token: $token")
        return token
    }

    fun clearAuthToken() {
        val editor = prefs.edit()
        editor.remove(USER_TOKEN)
        editor.apply()
        Log.d("AuthToken", "Token removido")
    }
}