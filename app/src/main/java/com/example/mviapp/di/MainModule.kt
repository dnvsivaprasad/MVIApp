package com.example.mviapp.di

import com.example.mviapp.data.api.ApiInterface
import com.example.mviapp.data.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiInterface {
        return Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build().create(ApiInterface::class.java)
    }

    @Provides
    fun getPostRepository(apiInterface: ApiInterface): PostRepository {
        return PostRepository(apiInterface)
    }
}