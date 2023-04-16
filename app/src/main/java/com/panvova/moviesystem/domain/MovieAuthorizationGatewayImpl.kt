package com.panvova.moviesystem.domain

import com.panvova.moviesystem.data.MovieAuthorizationGateway
import com.panvova.moviesystem.data.MovieSystemAPI
import com.panvova.moviesystem.data.toDomain
import com.panvova.moviesystem.domain.models.User
import com.panvova.moviesystem.utils.InvalidCredentialsException
import com.panvova.moviesystem.utils.UserAlreadyExistsException

class MovieAuthorizationGatewayImpl(private val api: MovieSystemAPI) : MovieAuthorizationGateway {

    override suspend fun login(username: String, password: String): User {
        val users = api.getUsers().toDomain() // TODO mapping
        val user = users.find { it.username == username }
        return if (user != null && user.password == password) {
            user
        } else {
            throw InvalidCredentialsException("Invalid username or password.")
        }
    }

    override suspend fun register(username: String, password: String, email: String) {
        val users = api.getUsers().toDomain()
        val user = users.find { it.username == username }
        if (user != null) {
            throw UserAlreadyExistsException("User with this username already exists.")
        }
        val newUser = User(username = username, password = password, email = email)
        api.createNewUser(newUser)
    }
}