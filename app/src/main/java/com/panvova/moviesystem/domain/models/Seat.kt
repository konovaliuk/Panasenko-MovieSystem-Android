package com.panvova.moviesystem.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Seat(
    val id: Int = 0,
    val rowNumberSeat: Int,
    val columnNumberSeat: Int,
    val isAvailable: Boolean = true,
) : Parcelable