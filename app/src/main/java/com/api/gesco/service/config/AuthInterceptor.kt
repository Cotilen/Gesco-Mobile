package com.api.gesco.service.config

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context): Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // Adiciona o token no cabeçalho
        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        // Processa a requisição
        val response = chain.proceed(requestBuilder.build())

        // Se o token expirou, remove o token da sessão
        if (response.code == 403) {
            sessionManager.clearAuthToken()
        }

        return response
    }
}
