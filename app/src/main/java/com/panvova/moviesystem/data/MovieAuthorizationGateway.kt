package com.panvova.moviesystem.data

import com.panvova.moviesystem.domain.models.User

interface MovieAuthorizationGateway {
    suspend fun login(username: String, password: String): User
    suspend fun register(username: String, password: String, email: String)
}
