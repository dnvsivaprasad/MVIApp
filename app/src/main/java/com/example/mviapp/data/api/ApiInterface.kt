package com.example.mviapp.data.api

import com.example.mviapp.data.model.PostModel
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    suspend fun getPosts(): List<PostModel>
}