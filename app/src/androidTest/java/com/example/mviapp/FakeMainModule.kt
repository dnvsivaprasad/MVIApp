package com.example.mviapp

import com.example.mviapp.data.api.ApiInterface
import com.example.mviapp.data.repository.PostRepository
import com.example.mviapp.di.MainModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [MainModule::class]
)
@Module
object FakeMainModule {
    @Provides
    @Singleton
    fun provideFakeApiService(): ApiInterface {
        return Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build().create(ApiInterface::class.java)
    }

    @Provides
    fun getFakePostRepository(apiInterface: ApiInterface): PostRepository {
        return PostRepository(apiInterface)
    }
}