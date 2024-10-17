package com.api.gesco.service.response.frequency

data class FrequencyResponse(
    val id: Int,
    val dia: String,
    val aluno: Int,
    val disciplina: String,
    val professor: String,
    val presenca: String
)
