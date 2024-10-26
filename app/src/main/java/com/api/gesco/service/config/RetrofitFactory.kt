package com.api.gesco.service.config

import android.content.Context
import com.api.gesco.service.ActivitiesService
import com.api.gesco.service.AlunoService
import com.api.gesco.service.AuthService
import com.api.gesco.service.GridHourService
import com.api.gesco.service.response.aluno.AlunoResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory(private val context: Context) {
        private val URL_BASE = "http://10.0.2.2:8080/"
    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient(context))
        .build()


    private fun okHttpClient(context: Context?): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(40, TimeUnit.SECONDS)
            .connectTimeout(40, TimeUnit.SECONDS)

        context?.let {
            builder.addInterceptor(AuthInterceptor(it))
        }

        return builder.build()
    }

    fun authService(): AuthService{
        return retrofitFactory.create(AuthService::class.java)
    }

    fun gridHourService(): GridHourService{
        return retrofitFactory.create(GridHourService::class.java)
    }

    fun alunoService(): AlunoService{
        return retrofitFactory.create((AlunoService::class.java))
    }

    fun ActivitiesService(): ActivitiesService{
        return retrofitFactory.create(ActivitiesService::class.java)
    }


}

