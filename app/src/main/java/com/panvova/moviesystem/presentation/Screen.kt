package com.panvova.moviesystem.presentation

enum class Screen(val route: String) {
    LOGIN("login"),
    REGISTER("register"),
    HOME("home"),
    MOVIE_DETAILS_SEAT_SELECTION("movie_details"),
    CHECKOUT("checkout")
}