package com.panvova.moviesystem.data

import com.panvova.moviesystem.data.models.MoviesResponse
import com.panvova.moviesystem.data.models.SeatsResponse
import com.panvova.moviesystem.data.models.UsersResponse
import com.panvova.moviesystem.domain.models.Seat
import com.panvova.moviesystem.domain.models.User
import retrofit2.http.*

interface MovieSystemAPI {

    @GET("seats?size=${SEATS_SIZE}")
    suspend fun getSeats(): SeatsResponse

    @POST("seats/{id}")
    suspend fun updateSeat(@Path("id") seatId: Int, @Body data: Seat): Seat

    @GET("movies")
    suspend fun getMovies(): MoviesResponse

    @GET("users")
    suspend fun getUsers(): UsersResponse

    @POST("users")
    suspend fun createNewUser(@Body request: User)
}