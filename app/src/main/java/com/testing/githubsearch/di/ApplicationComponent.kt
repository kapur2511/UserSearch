package com.testing.githubsearch.di

import com.testing.githubsearch.GitUsersApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SearchBindingModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface ApplicationComponent: AndroidInjector<GitUsersApplication> {

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
        @BindsInstance
        fun application(application: GitUsersApplication): Builder
    }

}