package com.api.gesco.service

import com.api.gesco.model.ModelLogin
import com.api.gesco.service.response.gridHour.GridHourResponseList
import com.api.gesco.service.response.login.AuthTokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GridHourService {

    @GET("grade-horario/turma/{id}")
    fun getGridHour(@Path("id") id: Int):Call<GridHourResponseList>

}