package com.panvova.moviesystem.presentation.features.login


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun LoginScreen(
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LoginScreenInternal(
        onLoginClicked = { username, password ->
            if (username.isBlank() || username.length < 4) {
                Toast.makeText(context, "Username is short", Toast.LENGTH_SHORT).show()
            } else if (password.isBlank() || password.length < 4) {
                Toast.makeText(context, "Password is short", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.login(username, password) { success ->
                    if (success) {
                        onLoginClicked()
                    }
                }
            }
        },
        onRegisterClicked = onRegisterClicked
    )
}

@Composable
private fun LoginScreenInternal(
    onLoginClicked: (email: String, password: String) -> Unit,
    onRegisterClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App name
        Text(
            text = "Movie System",
            style = MaterialTheme.typography.h4
        )

        // Username field
        var username by remember { mutableStateOf("") }
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Password field
        var password by remember { mutableStateOf("") }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        // Login button
        Button(
            onClick = { onLoginClicked(username, password) },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Log in")
        }

        // Register button
        TextButton(
            onClick = { onRegisterClicked() },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Don't have an account? Register")
        }
    }
}
