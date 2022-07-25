package com.testing.githubsearch.util

sealed class ResultWrapper<T>

data class LoadingState<T>(
    val showLoading: Boolean
): ResultWrapper<T>()

data class SuccessState<T>(
    val data: T
): ResultWrapper<T>()

data class ErrorState<T>(
    val throwable: Throwable?
): ResultWrapper<T>()