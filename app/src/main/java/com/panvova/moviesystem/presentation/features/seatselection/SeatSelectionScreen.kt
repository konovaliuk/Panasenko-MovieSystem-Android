package com.panvova.moviesystem.presentation.features.seatselection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.panvova.moviesystem.domain.models.Schedule
import com.panvova.moviesystem.domain.models.Seat


@Composable
fun SeatSelectionScreen(
    schedule: Schedule,
    onPurchase: (List<Seat>) -> Unit
) {
    var selectedSeats by remember { mutableStateOf<List<Seat>>(mutableListOf()) }
    val availableSeats by remember { mutableStateOf(schedule.seats) }

    Text(
        text = "Select a seat",
        style = MaterialTheme.typography.h6,
        modifier = Modifier
            .padding(top = 16.dp, bottom = 8.dp)
    )
    Text(
        text = "Screen",
        style = MaterialTheme.typography.caption,
        modifier = Modifier
            .padding(bottom = 8.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))

    // Draw the seat layout row by row
    fun onSeatSelectedCompose(seat: Seat) {
        if (!seat.isAvailable) {
            // seat is not available, do not allow selection
            return
        }

        selectedSeats = if (selectedSeats.contains(seat)) {
            selectedSeats.filter { it != seat }.toMutableList()
        } else {
            (selectedSeats + seat).toMutableList()
        }
    }

    // Draw the seat layout
    SeatLayout(
        availableSeats = availableSeats,
        selectedSeats = selectedSeats,
        onSeatSelected = { seat: Seat -> onSeatSelectedCompose(seat = seat) }
    )

    val rows = availableSeats.groupBy { it.rowNumberSeat }

    // Show the selected seats
    if (selectedSeats.isNotEmpty()) {
        Text(
            text = "Selected seats: ${selectedSeats.joinToString(", ") { "Row: " + it.columnNumberSeat.toString() + " Column: " + it.rowNumberSeat.toString() }}",
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }

    // Show the purchase button
    Button(
        onClick = { onPurchase(selectedSeats) },
        enabled = selectedSeats.isNotEmpty(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = "Purchase")
    }

}


@Composable
fun SeatLayout(
    availableSeats: List<Seat>,
    selectedSeats: List<Seat>,
    onSeatSelected: (Seat) -> Unit,
) {
    val rows = availableSeats.groupBy { it.rowNumberSeat }
    val maxColumns = rows.values.maxOfOrNull { it.size } ?: 0

    // Draw the seat layout row by row
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        for (row in rows.keys.sorted()) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                val seatsInRow = rows[row] ?: emptyList()

                // Draw the seat name label for the row
                Text(
                    text = "Row $row",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(end = 16.dp)
                )

                // Draw each seat in the row
                for (column in 1..maxColumns) {
                    val seat = seatsInRow.find { it.columnNumberSeat == column }
                    val isSelected = selectedSeats.contains(seat)
                    val isAvailable = seat != null && seat.isAvailable

                    // Draw the seat with the correct style based on availability and selection
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .padding(4.dp)
                            .background(
                                color = when {
                                    isSelected -> Color.Green
                                    !isAvailable -> Color.Red
                                    else -> Color.LightGray
                                },
                                shape = CircleShape
                            )
                            .clickable(onClick = {
                                onSeatSelected(seat!!)
                            })
                    ) {
                        if (seat != null) {
                            Text(
                                text = seat.columnNumberSeat.toString(),
                                style = MaterialTheme.typography.body2,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}
