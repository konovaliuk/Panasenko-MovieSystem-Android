package com.panvova.moviesystem.presentation.features.register

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panvova.moviesystem.data.MovieAuthorizationGateway
import com.panvova.moviesystem.utils.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val gateway: MovieAuthorizationGateway,
    @ApplicationContext private val context: Context
) : ViewModel() {

    fun register(username: String, password: String, email: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                gateway.register(username, password, email)
                callback(true) // success
            } catch (e: Exception) {
                print(e)
                showToast(context, e.message.toString())
                callback(false) // failure
            }
        }
    }
}