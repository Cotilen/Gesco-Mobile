package com.api.gesco.service.response.events

import java.util.Objects

data class EventsResponseList(
    val headers:  Map<String, String>,
    val body: Events,
    val statuscode: String,
    val statusCodevalue: Int
)
