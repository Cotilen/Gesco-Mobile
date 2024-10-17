package com.api.gesco.service.response.frequency

import java.util.Objects

data class FrequencyResponseList(
    val headers:  Map<String, String>,
    val body: List<FrequencyResponse>,
    val statuscode: String,
    val statusCodevalue: Int
)
