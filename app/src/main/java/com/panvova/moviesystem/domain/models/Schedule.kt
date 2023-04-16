package com.panvova.moviesystem.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Schedule(
    val id: Int,
    val movie: Movie,
    val startTime: LocalDateTime,
    val seats: List<Seat>,
) : Parcelable