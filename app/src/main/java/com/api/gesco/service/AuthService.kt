package com.api.gesco.service

import com.api.gesco.model.ModelLogin
import com.api.gesco.service.response.login.AuthTokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("aluno/auth/login")
    fun authTeacher(@Body body: ModelLogin):Call<AuthTokenResponse>

}