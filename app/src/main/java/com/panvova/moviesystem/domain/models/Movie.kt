package com.panvova.moviesystem.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Movie(
    val id: Int = 0,
    val title: String = "",
    val director: String = "",
    val releaseYear: Int = 0,
    val rating: Float = 0.0F,
    val description: String = "",
    val genre: String = "",
    val posterUrl: String = "",
) : Parcelable