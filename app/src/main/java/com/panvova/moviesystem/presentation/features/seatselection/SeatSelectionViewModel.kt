package com.panvova.moviesystem.presentation.features.seatselection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panvova.moviesystem.data.MovieSystemGateway
import com.panvova.moviesystem.domain.models.Seat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeatSelectionViewModel @Inject constructor(
    private val gateway: MovieSystemGateway,
) : ViewModel() {

    fun updateSeats(seatIds: List<Int>, seats: List<Seat>) {
        viewModelScope.launch {
            gateway.updateSeats(seatIds, seats)
        }
    }
}