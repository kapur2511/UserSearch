package com.testing.githubsearch.di

import com.testing.githubsearch.data.api.SearchApi
import com.testing.githubsearch.data.datasource.SearchDataSourceImpl
import com.testing.githubsearch.data.datasource.SearchDatasource
import com.testing.githubsearch.data.repositories.SearchRepository
import com.testing.githubsearch.data.repositories.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

@Module
class SearchModule {

    private val baseUrl = "https://api.github.com/"

    @Provides
    fun providesSearchApi(
        okHttpClient: OkHttpClient,
        factory: Converter.Factory
    ): SearchApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(factory)
        .client(okHttpClient)
        .build()
        .create(SearchApi::class.java)

    @Provides
    fun provideSearchDataSource(
        searchApi: SearchApi
    ): SearchDatasource = SearchDataSourceImpl(searchApi = searchApi)

    @Provides
    fun providesSearchRepository(
        searchDatasource: SearchDatasource
    ): SearchRepository = SearchRepositoryImpl(
        searchDatasource
    )
}