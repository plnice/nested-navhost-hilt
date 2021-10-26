package com.github.plnice.nestednavhosthilt.main.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Dashboard(viewModel: DashboardViewModel) {
    val uiState by viewModel.uiStates.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Dashboard") }) },
        content = {
            when (val state = uiState) {
                UiState.Loading -> Loading()
                UiState.Error -> Error()
                is UiState.Loaded -> Loaded(state.items)
            }
        }
    )
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
private fun Error() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Button(modifier = Modifier.align(Alignment.Center), onClick = {}) {
            Text("Retry")
        }
    }
}

@Composable
fun Loaded(items: List<String>) {
    Row(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(items) { item -> Text(item) }
        }
    }
}
