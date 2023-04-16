package com.panvova.moviesystem.presentation.features.register

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun RegisterLoginScreen(
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    RegisterLoginScreenInternal(
        onLoginClicked = onLoginClicked,
        onRegisterClicked = { username, email, password ->
            if (username.isBlank() || username.length < 6) {
                Toast.makeText(context, "Username is short", Toast.LENGTH_SHORT).show()
            } else if (password.isBlank() || password.length < 6) {
                Toast.makeText(context, "Password is short", Toast.LENGTH_SHORT).show()
            } else if (email.isBlank() || email.length < 6) {
                Toast.makeText(context, "Email is short", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.register(username, password, email) { success ->
                    if (success) {
                        onRegisterClicked()
                    }
                }
            }
        }
    )
}

@Composable
private fun RegisterLoginScreenInternal(
    onRegisterClicked: (username: String, email: String, password: String) -> Unit,
    onLoginClicked: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Welcome to Movie System",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(vertical = 32.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = emailState,
            onValueChange = { emailState = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = passwordState,
            onValueChange = { passwordState = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Button(
            onClick = { onRegisterClicked(username, emailState, passwordState) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            Text("Register")
        }

        TextButton(
            onClick = onLoginClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Already have an account? Log in")
        }
    }
}