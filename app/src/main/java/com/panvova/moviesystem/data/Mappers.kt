package com.panvova.moviesystem.data

import com.panvova.moviesystem.data.models.MoviesResponse
import com.panvova.moviesystem.data.models.SeatsResponse
import com.panvova.moviesystem.data.models.UsersResponse
import com.panvova.moviesystem.domain.models.Movie
import com.panvova.moviesystem.domain.models.Seat
import com.panvova.moviesystem.domain.models.User

internal const val SEATS_SIZE = 784


fun UsersResponse.toDomain(): List<User> {
    return _embedded.users
}

fun SeatsResponse.toDomain(): List<Seat> {
    return _embedded.seats
}

fun MoviesResponse.toDomain(): List<Movie> {
    return _embedded.movies
}