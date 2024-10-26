package com.api.gesco.service.response.activities

data class ActivitiesResponse(
    val id: Int,
    val nome: String,
    val descricao: String,
    val data_atividade: String,
    val valor: Float,
    val professor: String,
    val tipo: String
)
