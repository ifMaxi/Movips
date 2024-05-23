package com.maxidev.movips.core.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.maxidev.movips.core.data.remote.AuthorizationInterceptor
import com.maxidev.movips.movies.data.remote.MoviesRemoteApiService
import com.maxidev.movips.core.utils.Constants.BASE_URL
import com.maxidev.movips.core.utils.Constants.CONTENT_TYPE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {

        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        val okHttp = OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(
                json.asConverterFactory(
                    CONTENT_TYPE.toMediaType()
                )
            )
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): MoviesRemoteApiService =
        retrofit.create(MoviesRemoteApiService::class.java)
}