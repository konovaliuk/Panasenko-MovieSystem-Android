package com.panvova.moviesystem.presentation.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panvova.moviesystem.data.MovieSystemGateway
import com.panvova.moviesystem.domain.models.ScheduleOfTheDay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gateway: MovieSystemGateway,
) : ViewModel() {

    private val _schedules = MutableStateFlow<List<ScheduleOfTheDay>>(emptyList())

    fun loadSchedule(): List<ScheduleOfTheDay> {
        viewModelScope.launch {
            _schedules.value = gateway.getSchedules()
        }
        return _schedules.value
    }
}