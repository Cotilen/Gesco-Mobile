package com.api.gesco.service.response.gridHour

import java.util.Objects

data class GridHourResponseList(
    val headers:  Map<String, String>,
    val body: List<GridHourResponse>,
    val statuscode: String,
    val statusCodevalue: Int
)
