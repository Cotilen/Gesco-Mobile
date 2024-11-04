package com.api.gesco.service

import com.api.gesco.model.ModelLogin
import com.api.gesco.service.response.aluno.AlunoResponse
import com.api.gesco.service.response.discipline.DisciplineResponseList
import com.api.gesco.service.response.events.EventsResponse
import com.api.gesco.service.response.events.EventsResponseList
import com.api.gesco.service.response.frequency.FrequencyDataResponseList
import com.api.gesco.service.response.frequency.FrequencyResponseList
import com.api.gesco.service.response.gridHour.GridHourResponseList
import com.api.gesco.service.response.login.AuthTokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AlunoService {

    @GET("aluno/user")
    fun getAluno():Call<AlunoResponse>

    @GET("aluno/eventos/{id}")
    fun getEvents(@Path("id") id: Int): Call<EventsResponseList>

    @GET("aluno/disciplina/{id}")
    fun getDisciplines(@Path("id") id: Int): Call<DisciplineResponseList>

    @GET("aluno/{aluno}/frequencia/{disciplina}")
    fun getFrequencys(@Path("aluno") aluno: Int, @Path("disciplina") disciplina: Int): Call<FrequencyResponseList>

    @GET("aluno/frequencia/{id}")
    fun getFrequencyData(@Path("id") discipline: Int): Call<FrequencyDataResponseList>
}