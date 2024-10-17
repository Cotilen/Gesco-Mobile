package com.api.gesco.calls

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.api.gesco.model.ModelLogin
import com.api.gesco.service.config.RetrofitFactory
import com.api.gesco.service.config.SessionManager
import com.api.gesco.service.response.login.AuthTokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun loginStudent(body: ModelLogin, context: Context, preferences: SharedPreferences): AuthTokenResponse {
    return withContext(Dispatchers.IO) {
        try {
            val call = RetrofitFactory(context).authService().authTeacher(body).execute()

            if (call.isSuccessful) {
                val token = call.body()?.token
                if (token != null) {
                    val sessionManager = SessionManager(context)
                    sessionManager.saveAuthToken(token)
                    Log.d("AuthToken", "Token saved: $token")
                    AuthTokenResponse(token) // Retorna um AuthTokenResponse com o token
                } else {
                    Log.e("AuthToken", "O token é nulo")
                    AuthTokenResponse("") // Retorna um token vazio em caso de falha
                }
            } else {
                if (call.code() == 403) {
                    val callV2 = RetrofitFactory(context).authService().authTeacher(body).execute()
                    val tokenV2 = callV2.body()?.token
                    if (tokenV2 != null) {
                        val sessionManager = SessionManager(context)
                        sessionManager.saveAuthToken(tokenV2)
                        Log.d("AuthToken", "Token saved: $tokenV2")
                        return@withContext AuthTokenResponse(tokenV2) // Retorna o novo token
                    } else {
                        Log.e("AuthToken", "O token é nulo na segunda tentativa")
                        return@withContext AuthTokenResponse("") // Retorna um token vazio em caso de falha
                    }
                } else {
                    Log.e("AuthToken", "Erro na chamada da API: ${call.code()}")
                    return@withContext AuthTokenResponse("") // Retorna um token vazio em caso de erro
                }
            }

        } catch (e: HttpException) {
            Log.e("LinesScreen", "Erro na chamada da API: ${e.message}")
            return@withContext AuthTokenResponse("") // Retorna um token vazio em caso de exceção
        } catch (e: Exception) {
            Log.e("LinesScreen", "Erro desconhecido: ${e.message}")
            return@withContext AuthTokenResponse("") // Retorna um token vazio em caso de exceção
        }
    }
}
