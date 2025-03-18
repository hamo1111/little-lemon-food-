package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    val isLoggedIn = sharedPreferences.getBoolean("userRegistered", true)

    NavHost(navController = navController, startDestination = if (isLoggedIn) {
        Home.route
    } else {
        Onboarding.route
    }) {
        composable(route = Onboarding.route) { Onboarding(context, navController) }
        composable(route = Home.route) { Home(navController = navController) }
        composable(route = Profile.route) { Profile(context, navController) }
    }
}