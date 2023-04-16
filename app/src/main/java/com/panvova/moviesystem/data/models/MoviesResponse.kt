package com.panvova.moviesystem.data.models

import com.panvova.moviesystem.domain.models.Movie
import com.panvova.moviesystem.domain.models.Seat

data class MoviesResponse(
    val _embedded: MoviesEmbedded,
)

data class MoviesEmbedded(
    val movies: List<Movie>
)
