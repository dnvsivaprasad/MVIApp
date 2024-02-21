package com.example.mviapp.data.repository

import com.example.mviapp.data.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository(private val apiInterface: ApiInterface) {
    suspend fun getPosts() = withContext(Dispatchers.IO) { apiInterface.getPosts() }
}