package com.testing.githubsearch.di

import android.content.Context
import com.testing.githubsearch.GitUsersApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun appContext(application: GitUsersApplication): Context = application
}