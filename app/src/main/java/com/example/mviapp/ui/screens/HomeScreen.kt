package com.example.mviapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.mviapp.ui.main.intent.MainIntent
import com.example.mviapp.ui.main.viewmodel.PostViewModel
import com.example.mviapp.ui.main.viewstate.MainViewState

@Composable
fun HomeScreen(viewModel: PostViewModel, modifier: Modifier = Modifier) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is MainViewState.Loading -> LoadingView()
        is MainViewState.Error -> ErrorView(error = (state as MainViewState.Error).message)
        is MainViewState.Success -> PostView(list = (state as MainViewState.Success).data)
        MainViewState.Idle -> {}
    }
    LaunchedEffect(Unit) {
        viewModel.mainIntent.send(MainIntent.GetPosts)
    }
}