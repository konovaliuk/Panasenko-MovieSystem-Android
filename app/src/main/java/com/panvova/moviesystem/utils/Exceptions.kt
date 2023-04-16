package com.panvova.moviesystem.utils


class InvalidCredentialsException(message: String) : Exception(message)

class UserAlreadyExistsException(message: String) : Exception(message)