package com.example.intricare.utils

import android.content.Context
import com.example.intricare.data.LoginRequest

class UserCredPreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

    fun saveCredentials(username: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.apply()
    }

    fun getCredentials(): LoginRequest {
        val username = sharedPreferences.getString("username", "") ?: ""
        val password = sharedPreferences.getString("password", "") ?: ""
        return LoginRequest(username, password)
    }

    fun clearCredentials() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

}