package com.api.gesco.service

import com.api.gesco.service.response.activities.ActivitiesResponseList
import com.api.gesco.service.response.aluno.AlunoResponse
import com.api.gesco.service.response.discipline.DisciplineResponseList
import com.api.gesco.service.response.events.EventsResponseList
import com.api.gesco.service.response.frequency.FrequencyResponseList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ActivitiesService {

    @GET("atividade/turma/{id}")
    fun getAtivities(@Path("id") id: Int): Call<ActivitiesResponseList>
}