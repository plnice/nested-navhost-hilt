package com.github.plnice.nestednavhosthilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.plnice.nestednavhosthilt.authentication.Authentication
import com.github.plnice.nestednavhosthilt.main.Main
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            navigator.events.collectAsState().value.also { destination ->
                if (destination != Destinations.default) {
                    navController.navigate(destination.route)
                }
            }

            NavHost(navController, startDestination = Destinations.authentication.route) {
                composable(Destinations.authentication.route) { Authentication(viewModel = hiltViewModel()) }
                composable(Destinations.main.route) { Main() }
            }
        }
    }
}
