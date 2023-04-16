package com.panvova.moviesystem.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.panvova.moviesystem.domain.models.Schedule
import com.panvova.moviesystem.domain.models.Seat
import com.panvova.moviesystem.presentation.features.home.HomeScreen
import com.panvova.moviesystem.presentation.features.login.LoginScreen
import com.panvova.moviesystem.presentation.features.register.RegisterLoginScreen
import com.panvova.moviesystem.presentation.features.checkout.CheckoutScreen
import com.panvova.moviesystem.presentation.features.seatselection.MovieDetailsScreen

lateinit var scheduleUpdated: Schedule
lateinit var purchasedSeats: List<Seat>

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.LOGIN.route) {
        composable(Screen.LOGIN.route) {
            LoginScreen(
                onLoginClicked = { navController.navigate(Screen.HOME.route) },
                onRegisterClicked = { navController.navigate(Screen.REGISTER.route) }
            )
        }
        composable(Screen.REGISTER.route) {
            RegisterLoginScreen(
                onRegisterClicked = { navController.navigate(Screen.HOME.route) },
                onLoginClicked = { navController.navigate(Screen.LOGIN.route) }
            )
        }
        composable(Screen.HOME.route) {
            HomeScreen(
                onItemClicked = { schedule ->
                    scheduleUpdated = schedule
                    navController.navigate(Screen.MOVIE_DETAILS_SEAT_SELECTION.route)
                }
            )
        }
        composable(Screen.MOVIE_DETAILS_SEAT_SELECTION.route) {
            MovieDetailsScreen(
                schedule = scheduleUpdated,
                onPurchase = { seats ->
                    purchasedSeats = seats
                    navController.navigate(Screen.CHECKOUT.route)
                }
            )
        }
        composable(Screen.CHECKOUT.route) {
            CheckoutScreen(
                selectedSeats = purchasedSeats,
                onPaymentComplete = { navController.navigate(Screen.HOME.route) },
            )
        }
    }
}
