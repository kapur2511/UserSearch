package com.testing.githubsearch.di

import com.testing.githubsearch.data.api.ApiHeaderInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(ApiHeaderInterceptor())
            .build()

    @Provides
    fun provideConverterFactory(): Converter.Factory =
        GsonConverterFactory.create()
}