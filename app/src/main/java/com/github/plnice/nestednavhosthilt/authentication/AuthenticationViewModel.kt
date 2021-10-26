package com.github.plnice.nestednavhosthilt.authentication

import androidx.lifecycle.ViewModel
import com.github.plnice.nestednavhosthilt.Destinations
import com.github.plnice.nestednavhosthilt.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    fun authenticate() {
        navigator.navigate(Destinations.main)
    }
}
