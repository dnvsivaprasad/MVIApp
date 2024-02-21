package com.example.mviapp.ui.main.viewstate

import com.example.mviapp.data.model.PostModel

sealed class MainViewState {
    object Idle : MainViewState()
    object Loading : MainViewState()
    class Error(val message: String?) : MainViewState()
    class Success(val data: List<PostModel>) : MainViewState()
}