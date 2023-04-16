package com.panvova.moviesystem.presentation.features.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panvova.moviesystem.data.MovieAuthorizationGateway
import com.panvova.moviesystem.utils.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val gateway: MovieAuthorizationGateway,
    @ApplicationContext private val context: Context
) : ViewModel() {

    fun login(username: String, password: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                gateway.login(username, password)
                callback(true) // success
            } catch (e: Exception) {
                print(e)
                showToast(context, e.message.toString())
                callback(false) // failure
            }
        }
    }
}