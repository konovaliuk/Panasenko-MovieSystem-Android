package com.panvova.moviesystem.data

import com.panvova.moviesystem.domain.models.ScheduleOfTheDay
import com.panvova.moviesystem.domain.models.Seat

interface MovieSystemGateway {
    suspend fun updateSeats(seatIds: List<Int>, seats: List<Seat>)
    suspend fun getSchedules(): List<ScheduleOfTheDay>
}
