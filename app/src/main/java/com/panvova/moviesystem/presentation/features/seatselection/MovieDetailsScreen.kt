package com.panvova.moviesystem.presentation.features.seatselection

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.panvova.moviesystem.domain.models.Movie
import com.panvova.moviesystem.domain.models.Schedule
import com.panvova.moviesystem.domain.models.Seat

@Composable
fun MovieDetailsScreen(
    schedule: Schedule,
    onPurchase: (List<Seat>) -> Unit,
    viewModel: SeatSelectionViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { MovieDetailsTopBar(schedule.movie.title) },
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues: PaddingValues ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            MovieDetailsScreenInternal(movie = schedule.movie)
            SeatSelectionScreen(
                schedule = schedule,
                onPurchase = { seats ->
                    viewModel.updateSeats(seats.map { seat -> seat.id }, seats)
                    onPurchase(seats)
                })
        }
    }
}

@Composable
fun MovieDetailsTopBar(title: String) {
    TopAppBar(
        title = { Text(text = title) },
    )
}

@Composable
fun MovieDetailsScreenInternal(movie: Movie) {
    AsyncImage(
        model = movie.posterUrl,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.67f)
            .clip(shape = RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = movie.title,
        style = MaterialTheme.typography.h5
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Genre: ${movie.genre}",
        style = MaterialTheme.typography.subtitle1
    )
    Spacer(modifier = Modifier.height(8.dp))
}
