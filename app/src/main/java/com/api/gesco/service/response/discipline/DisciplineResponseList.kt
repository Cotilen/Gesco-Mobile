package com.api.gesco.service.response.discipline

data class DisciplineResponseList(
    val headers:  Map<String, String>,
    val body: List<DisciplineResponse>,
    val statuscode: String,
    val statusCodevalue: Int
)
