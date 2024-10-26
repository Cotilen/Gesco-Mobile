package com.api.gesco.service.response.activities

data class ActivitiesResponseList(
    val headers:  Map<String, String>,
    val body: List<ActivitiesResponse>,
    val statuscode: String,
    val statusCodevalue: Int
)
