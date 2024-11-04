package com.api.gesco.model

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.api.gesco.calls.getActivities
import com.api.gesco.calls.getDataFrequency
import com.api.gesco.calls.getDisciplines
import com.api.gesco.calls.getEvents
import com.api.gesco.calls.getFrequency
import com.api.gesco.calls.getGridHour
import com.api.gesco.calls.getStudent
import com.api.gesco.calls.loginStudent
import com.api.gesco.service.response.activities.ActivitiesResponseList
import com.api.gesco.service.response.aluno.AlunoResponse
import com.api.gesco.service.response.discipline.DisciplineResponseList
import com.api.gesco.service.response.events.Events
import com.api.gesco.service.response.events.EventsResponseList
import com.api.gesco.service.response.frequency.FrequencyDataResponse
import com.api.gesco.service.response.frequency.FrequencyDataResponseList
import com.api.gesco.service.response.frequency.FrequencyResponseList
import com.api.gesco.service.response.gridHour.GridHourResponseList
import com.api.gesco.service.response.login.AuthTokenResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Objects

class MyViewModel : ViewModel() {
    var alunoResponse = AlunoResponse(
        0,"","","","","",0,"",0,"","","","","","","",0,"","",0,0
    )
    var gridHour = GridHourResponseList(emptyMap(), emptyList(), "", 0)

    var eventsList = EventsResponseList(emptyMap(), Events(emptyList()), "", 0)
    var disciplineList = DisciplineResponseList(emptyMap(), emptyList(), "", 0)
    var frequencyList = FrequencyResponseList(emptyMap(), emptyList(), "", 0)
    var frequencyDataList = FrequencyDataResponse(0,0,0)
    var activitiesList = ActivitiesResponseList(emptyMap(), emptyList(), "", 0)


    private val _login = MutableStateFlow<AuthTokenResponse>(AuthTokenResponse(""))
    val login: StateFlow<AuthTokenResponse> get() = _login

    private val _aluno = MutableStateFlow<AlunoResponse>(alunoResponse)
    val aluno: StateFlow<AlunoResponse> get() = _aluno

    private val _grid = MutableStateFlow<GridHourResponseList>(gridHour)
    val grid: StateFlow<GridHourResponseList> get() = _grid

    private val _events = MutableStateFlow<EventsResponseList>(eventsList)
    val events: StateFlow<EventsResponseList> get() = _events

    private val _discipline = MutableStateFlow<DisciplineResponseList>(disciplineList)
    val discipline: StateFlow<DisciplineResponseList> get() = _discipline

    private val _frequency = MutableStateFlow<FrequencyResponseList>(frequencyList)
    val frequency: StateFlow<FrequencyResponseList> get() = _frequency

    private val _frequencyData = MutableStateFlow<FrequencyDataResponse>(frequencyDataList)
    val frequencyData: StateFlow<FrequencyDataResponse> get() = _frequencyData

    private val _activities = MutableStateFlow<ActivitiesResponseList>(activitiesList)
    val activities: StateFlow<ActivitiesResponseList> get() = _activities

    fun fetchLogin(body: ModelLogin, context: Context, preferences: SharedPreferences) {
        viewModelScope.launch {
            try {
                val result = loginStudent(body, context, preferences)
                _login.value = result
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun fetchAluno(context: Context){
        viewModelScope.launch {
            try{
                val result = getStudent(context)
                _aluno.value = result
            }catch (e: Exception){
                println(e)
            }
        }
    }

    fun fetchGrid(context: Context, turma: Int){
        viewModelScope.launch {
            try {
                val result = getGridHour(context, turma)
                _grid.value = result
            }catch (e: Exception){
                println(e)
            }
        }
    }

    fun fetchEvents(context: Context, escola: Int){
        viewModelScope.launch {
            try {
                val result = getEvents(context, escola)
                _events.value = result
            }catch (e: Exception){
                println(e)
            }
        }
    }

    fun fetchDisciplines(context: Context, aluno: Int){
        viewModelScope.launch {
            try {
                val result = getDisciplines(context, aluno)
                _discipline.value = result
            }catch (e: Exception){
                println(e)
            }
        }
    }

    fun fetchFrequency(context: Context, aluno: Int, disciplina: Int){
        viewModelScope.launch {
            try {
                val result = getFrequency(context,aluno,disciplina)
                _frequency.value = result
            }catch (e: Exception){
                println(e)
            }
        }
    }

    fun fetchActivities(context: Context, turma: Int){
        viewModelScope.launch {
            try {
                val result = getActivities(context, turma)
                _activities.value = result
            }catch (e: Exception){
                println(e)
            }
        }
    }

    fun fetchFrequencyData(context: Context, disciplina: Int){
        viewModelScope.launch {
            try {
                val result = getDataFrequency(context, disciplina)
                Log.e("teste", "$result")
                _frequencyData.value = result.body[0]
            }catch (e: Exception){
                Log.e("erro", "$e")
                println(e)
            }
        }
    }
}