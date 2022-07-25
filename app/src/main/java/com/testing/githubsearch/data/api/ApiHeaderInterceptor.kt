package com.testing.githubsearch.data.api

import com.testing.githubsearch.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiHeaderInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequestBuilder = request.newBuilder()
        // Add your own key in keys.properties and the uncomment this code.
        // Authorised requests will have a higher rate limit as compared to
        // unauthorised requests
        // newRequestBuilder.addHeader(AUTHORIZATION_KEY, BuildConfig.GIT_HUB_API_KEY)
        newRequestBuilder.addHeader(ACCEPT_KEY, ACCEPT_HEADER)
        return chain.proceed(newRequestBuilder.build())
    }

    companion object {
        private const val ACCEPT_HEADER = "application/vnd.github+json"
        private const val ACCEPT_KEY = "Accept"
        private const val AUTHORIZATION_KEY = "Authorization"
    }
}