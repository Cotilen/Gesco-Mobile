package com.api.gesco.service.response.events

data class EventsResponse(
    val id: Int,
    val nome: String,
    val descricao: String,
    val dia: String,
    val horario: String
)
