package com.api.gesco.service.response.gridHour

data class GridHourResponse(
    val id: Int,
    val hora: String,
    val dia: String,
    val professor: String,
    val disciplina: String,
    val turma: String
)
