package com.panvova.moviesystem.domain.models

import java.time.LocalDateTime

data class ScheduleOfTheDay(
    val schedules: List<Schedule>,
    val date: LocalDateTime
)
