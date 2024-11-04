package com.api.gesco.service.response.frequency

import java.util.Objects

data class FrequencyDataResponseList(
    val headers:  Map<String, String>,
    val body: List<FrequencyDataResponse>,
    val statuscode: String,
    val statusCodevalue: Int
)
