package com.example.mviapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.mviapp.data.model.PostModel

@Composable
fun PostView(modifier: Modifier = Modifier, list: List<PostModel>) {
    LazyColumn(modifier = Modifier.fillMaxSize().testTag("ListView")) {
        items(list) {
            PostItem(item = it)
        }
    }
}