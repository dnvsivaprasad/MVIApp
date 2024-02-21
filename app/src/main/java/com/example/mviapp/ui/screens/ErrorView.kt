package com.example.mviapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun ErrorView(modifier: Modifier = Modifier, error: String?) {
    Column(
        modifier = Modifier.fillMaxSize().testTag("ErrorView"),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
          Text(text = error.toString())
        Button(onClick = {  }) {
            Text(text = "Refresh")
        }
    }
}