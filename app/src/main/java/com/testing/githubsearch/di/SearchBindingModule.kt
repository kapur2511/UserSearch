package com.testing.githubsearch.di

import com.testing.githubsearch.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [
        SearchModule::class,
        NetworkModule::class,
        AppModule::class,
        ViewModelModule::class
    ]
)
abstract class SearchBindingModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}