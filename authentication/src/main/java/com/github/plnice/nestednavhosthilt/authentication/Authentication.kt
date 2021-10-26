package com.github.plnice.nestednavhosthilt.authentication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Authentication(viewModel: AuthenticationViewModel) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Authentication") }) },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Button(modifier = Modifier.align(Alignment.Center), onClick = viewModel::authenticate) {
                    Text("Login")
                }
            }
        }
    )
}
