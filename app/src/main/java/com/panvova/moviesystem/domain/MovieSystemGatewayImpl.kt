package com.panvova.moviesystem.domain

import com.panvova.moviesystem.data.MovieSystemAPI
import com.panvova.moviesystem.data.MovieSystemGateway
import com.panvova.moviesystem.data.toDomain
import com.panvova.moviesystem.domain.models.Movie
import com.panvova.moviesystem.domain.models.Schedule
import com.panvova.moviesystem.domain.models.ScheduleOfTheDay
import com.panvova.moviesystem.domain.models.Seat
import com.panvova.moviesystem.presentation.scheduleUpdated
import java.time.LocalDateTime

class MovieSystemGatewayImpl(private val api: MovieSystemAPI) : MovieSystemGateway {
    override suspend fun updateSeats(seatIds: List<Int>, seats: List<Seat>) {
        scheduleUpdated = scheduleUpdated.copy(
            seats = scheduleUpdated.seats.map { seat ->
                seats.find { it.id == seat.id } ?: seat
            }
        )
        //seats.forEachIndexed { index, seat -> api.updateSeat(seatIds[index], seats[index]) }
    }

    override suspend fun getSchedules(): List<ScheduleOfTheDay> {
        val seats = api.getSeats().toDomain()
        val movies = api.getMovies().toDomain()
        val schedules = createNewSchedule(seats, movies)
        val numberOfDaysAhead = 7

        val scheduleOfTheWeek = mutableListOf<ScheduleOfTheDay>()

        repeat(numberOfDaysAhead) { i ->
            scheduleOfTheWeek.add(
                ScheduleOfTheDay(
                    schedules = schedules,
                    date = LocalDateTime.now().plusDays(i.toLong())
                )
            )
        }

        return scheduleOfTheWeek
    }

    private fun createNewSchedule(seats: List<Seat>, movies: List<Movie>): List<Schedule> {
        val seatsWithIndexes = seats.mapIndexed { index, seat -> seat.copy(id = index) }
        val startTimesForSchedule = listOf(
            LocalDateTime.now().minusMinutes(16),
            LocalDateTime.now().plusHours(2),
            LocalDateTime.now().plusHours(4),
            LocalDateTime.now().plusHours(6),
            LocalDateTime.now().plusHours(8),
            LocalDateTime.now().plusHours(10),
        )

        return startTimesForSchedule.mapIndexed { index, startTime ->
            val startIndex = index * TOTAL_NUMBER
            val subListSeats = seatsWithIndexes.subList(
                fromIndex = startIndex,
                toIndex = startIndex + TOTAL_NUMBER
            )

            Schedule(
                id = index,
                startTime = startTime,
                seats = subListSeats,
                movie = movies[index]
            )
        }
    }

    companion object {
        private const val ROW_NUMBER = 4
        private const val COLUMN_NUMBER = 4
        private const val TOTAL_NUMBER = ROW_NUMBER * COLUMN_NUMBER

    }
}