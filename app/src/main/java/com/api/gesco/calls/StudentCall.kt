package com.api.gesco.calls

import android.content.Context
import android.util.Log
import com.api.gesco.service.config.RetrofitFactory
import com.api.gesco.service.response.activities.ActivitiesResponseList
import com.api.gesco.service.response.aluno.AlunoResponse
import com.api.gesco.service.response.discipline.DisciplineResponseList
import com.api.gesco.service.response.events.Events
import com.api.gesco.service.response.events.EventsResponseList
import com.api.gesco.service.response.frequency.FrequencyDataResponseList
import com.api.gesco.service.response.frequency.FrequencyResponseList
import com.api.gesco.service.response.gridHour.GridHourResponseList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun getStudent(context: Context): AlunoResponse {
    var alunoResponse = AlunoResponse(
        0,"","","","","",0,"",0,"","","","","","","",0,"","",0, 0
    )
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitFactory(context).alunoService().getAluno().execute()

            if(response.isSuccessful){
                println(response)
                response.body()!!
            }else{
                alunoResponse
            }

        } catch (e: HttpException) {
            Log.e("LinesScreen", "Erro na chamada da API: ${e.message}")
            return@withContext alunoResponse
        } catch (e: Exception) {
            Log.e("LinesScreen", "Erro desconhecido: ${e.message}")
            return@withContext alunoResponse
        }
    }
}

suspend fun getGridHour(context: Context, turma: Int): GridHourResponseList {
    var gridHour = GridHourResponseList(
        emptyMap(),
        emptyList(),
        "",
        0
    )
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitFactory(context).gridHourService().getGridHour(turma).execute()

            if(response.isSuccessful){
                println(response)
                response.body()!!
            }else{
                gridHour
            }

        } catch (e: HttpException) {
            Log.e("LinesScreen", "Erro na chamada da API: ${e.message}")
            return@withContext gridHour
        } catch (e: Exception) {
            Log.e("LinesScreen", "Erro desconhecido: ${e.message}")
            return@withContext gridHour
        }
    }
}

suspend fun getEvents(context: Context, escola: Int): EventsResponseList {
    var events = EventsResponseList(
        emptyMap(),
        Events(emptyList()),
        "",
        0
    )
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitFactory(context).alunoService().getEvents(escola).execute()

            if(response.isSuccessful){
                Log.e("fetch", "${response.body()}")
                response.body()!!
            }else{
                events
            }

        } catch (e: HttpException) {
            Log.e("LinesScreen", "Erro na chamada da API: ${e.message}")
            return@withContext events
        } catch (e: Exception) {
            Log.e("LinesScreen", "Erro desconhecido: ${e.message}")
            return@withContext events
        }
    }
}

suspend fun getDisciplines(context: Context, aluno: Int): DisciplineResponseList {
    var events = DisciplineResponseList(
        emptyMap(),
        emptyList(),
        "",
        0
    )
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitFactory(context).alunoService().getDisciplines(aluno).execute()

            if(response.isSuccessful){
                Log.e("fetch", "${response.body()}")
                response.body()!!
            }else{
                events
            }

        } catch (e: HttpException) {
            Log.e("LinesScreen", "Erro na chamada da API: ${e.message}")
            return@withContext events
        } catch (e: Exception) {
            Log.e("LinesScreen", "Erro desconhecido: ${e.message}")
            return@withContext events
        }
    }
}

suspend fun getFrequency(context: Context, aluno: Int, disciplina : Int): FrequencyResponseList {
    var events = FrequencyResponseList(
        emptyMap(),
        emptyList(),
        "",
        0
    )
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitFactory(context).alunoService().getFrequencys(aluno,disciplina).execute()

            if(response.isSuccessful){
                Log.e("fetch", "${response.body()}")
                response.body()!!
            }else{
                events
            }

        } catch (e: HttpException) {
            Log.e("LinesScreen", "Erro na chamada da API: ${e.message}")
            return@withContext events
        } catch (e: Exception) {
            Log.e("LinesScreen", "Erro desconhecido: ${e.message}")
            return@withContext events
        }
    }
}

suspend fun getActivities(context: Context, turma: Int): ActivitiesResponseList {
    var events = ActivitiesResponseList(
        emptyMap(),
        emptyList(),
        "",
        0
    )
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitFactory(context).ActivitiesService().getAtivities(turma).execute();

            if(response.isSuccessful){
                Log.e("fetch", "${response.body()}")
                response.body()!!
            }else{
                events
            }

        } catch (e: HttpException) {
            Log.e("LinesScreen", "Erro na chamada da API: ${e.message}")
            return@withContext events
        } catch (e: Exception) {
            Log.e("LinesScreen", "Erro desconhecido: ${e.message}")
            return@withContext events
        }
    }
}

suspend fun getDataFrequency(context: Context, disciplina: Int): FrequencyDataResponseList {
    var events = FrequencyDataResponseList(
        emptyMap(),
        emptyList(),
        "",
        0
    )
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitFactory(context).alunoService().getFrequencyData(disciplina).execute()

            if(response.isSuccessful){
                Log.e("fetch", "${response.body()}")
                response.body()!!
            }else{
                events
            }

        } catch (e: HttpException) {
            Log.e("LinesScreen", "Erro na chamada da API: ${e.message}")
            return@withContext events
        } catch (e: Exception) {
            Log.e("LinesScreen", "Erro desconhecido: ${e.message}")
            return@withContext events
        }
    }
}