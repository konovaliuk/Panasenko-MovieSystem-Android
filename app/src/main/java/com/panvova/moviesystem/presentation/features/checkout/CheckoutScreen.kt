package com.panvova.moviesystem.presentation.features.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.panvova.moviesystem.domain.models.Seat

@Composable
fun CheckoutScreen(
    selectedSeats: List<Seat>,
    onPaymentComplete: () -> Unit,
) {
    Scaffold(
        topBar = { CheckoutTopBar() },
        modifier = Modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Selected Seats:",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Column {
                selectedSeats.forEach { seat ->
                    Text(
                        text = "Row ${seat.rowNumberSeat}, Seat ${seat.columnNumberSeat}",
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
            Button(
                onClick = onPaymentComplete,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(text = "Screenshot now selected seats")
            }
        }
    }
}

@Composable
fun CheckoutTopBar() {
    TopAppBar(
        title = { Text(text = "Checkout") }
    )
}
