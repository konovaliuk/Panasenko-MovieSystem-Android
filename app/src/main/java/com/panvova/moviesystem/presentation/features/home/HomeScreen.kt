package com.panvova.moviesystem.presentation.features.home


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.panvova.moviesystem.domain.models.Schedule
import com.panvova.moviesystem.domain.models.ScheduleOfTheDay
import com.panvova.moviesystem.utils.showToast
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

@Composable
fun HomeScreen(
    onItemClicked: (Schedule) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val schedulesWithDate = viewModel.loadSchedule()
    HomeScreenInternal(
        schedulesWithDate = schedulesWithDate,
        onItemClicked = onItemClicked
    )
}

@Composable
fun HomeScreenInternal(
    schedulesWithDate: List<ScheduleOfTheDay>,
    onItemClicked: (Schedule) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movie System") },
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                schedulesWithDate.forEach { (schedules, date) ->
                    val dateFormatted =
                        date.dayOfWeek.toString().lowercase().capitalize(Locale.ROOT)
                    Text(
                        text = dateFormatted,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    LazyRow() {
                        items(schedules.size) { schedulesWithDateId ->
                            MovieCard(schedules[schedulesWithDateId], onItemClicked = onItemClicked)
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun MovieCard(schedule: Schedule, onItemClicked: (Schedule) -> Unit) {
    val context = LocalContext.current
    val movie = schedule.movie

    Card(
        modifier = Modifier
            .width(300.dp)
            .padding(8.dp)
            .clickable {
                val movieStartTimePlus15Minutes =
                    schedule.startTime.plus(15, ChronoUnit.MINUTES)
                val timeNow = LocalDateTime.now()
                if (timeNow > movieStartTimePlus15Minutes) {
                    showToast(context = context, "This movie is not available anymore")
                } else {
                    onItemClicked(schedule)
                }
            },
        elevation = 8.dp
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                modifier = Modifier
                    .height(150.dp)
                    .width(100.dp)
                    .padding(end = 8.dp)
            )

            Column {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Rating: ${movie.rating}",
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                val minutes =
                    if (schedule.startTime.minute < 10) {
                        "0" + schedule.startTime.minute.toString()
                    } else {
                        schedule.startTime.minute.toString()
                    }
                val startTime =
                    schedule.startTime.hour.toString() + ":" + minutes

                Text(
                    text = "Start time: $startTime",
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Magenta
                )
            }
        }
    }
}


