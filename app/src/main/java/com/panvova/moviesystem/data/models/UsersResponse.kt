package com.panvova.moviesystem.data.models

import com.panvova.moviesystem.domain.models.User

data class UsersResponse(
    val _embedded: UsersEmbedded,
)

data class UsersEmbedded(
    val users: List<User>
)
