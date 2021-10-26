package com.github.plnice.nestednavhosthilt.main.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.plnice.nestednavhosthilt.main.dashboard.UiState.Loaded
import com.github.plnice.nestednavhosthilt.main.dashboard.UiState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface UiState {
    object Loading : UiState
    object Error : UiState
    data class Loaded(val items: List<String>) : UiState
}

@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel() {

    private val _uiStates = MutableStateFlow<UiState>(Loading)
    val uiStates: StateFlow<UiState> = _uiStates.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1000)
            _uiStates.value = Loaded(listOf("Item 1", "Item 2", "Item 3"))
        }
    }
}
