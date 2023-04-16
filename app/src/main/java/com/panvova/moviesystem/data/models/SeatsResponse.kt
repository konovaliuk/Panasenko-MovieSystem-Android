package com.panvova.moviesystem.data.models

import com.panvova.moviesystem.domain.models.Seat

data class SeatsResponse(
    val _embedded: SeatsEmbedded,
)

data class SeatsEmbedded(
    val seats: List<Seat>
)
