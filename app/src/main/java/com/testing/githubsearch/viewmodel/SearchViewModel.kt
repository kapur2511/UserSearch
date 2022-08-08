package com.testing.githubsearch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testing.githubsearch.domain.GitUser
import com.testing.githubsearch.data.repositories.SearchRepository
import com.testing.githubsearch.util.ErrorState
import com.testing.githubsearch.util.LoadingState
import com.testing.githubsearch.util.ResultWrapper
import com.testing.githubsearch.util.SuccessState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
): ViewModel() {

    private val _searchResultObservable = MutableLiveData<ResultWrapper<List<GitUser>>>()
    val searchResultObservable: LiveData<ResultWrapper<List<GitUser>>> = _searchResultObservable
    private var job: Job? = null

    fun performSearch(
        searchText: String
    ) {
        if (searchText.length >= 3) {
            // valid use-case, let's show a loader
            _searchResultObservable.postValue(LoadingState(true))
            if (job != null && job!!.isActive) {
                // previous job/search is still running, let's cancel it
                Log.d(TAG, "Cancelling previous job $job")
                job!!.cancel()
            }
            job = viewModelScope.launch(Dispatchers.IO) {
                try {
                    val result = searchRepository.fetchUsers(searchText = searchText)
                    Log.d(TAG, "Result from API $result")
                    _searchResultObservable.postValue(result)
                } catch (e: CancellationException) {
                    /*no-op*/
                    // This will be caught when the previous job was cancelled due to a new search
                    // being performed. In that case we need not handle anything as the new search
                    // will override it.
                    Log.d(TAG, "CancellationException $e")
                } catch (e: Exception) {
                    Log.d(TAG, "Exception $e")
                    _searchResultObservable.postValue(ErrorState(Throwable("Something went wrong")))
                }
            }
        } else if (searchText.isEmpty()) {
            _searchResultObservable.postValue(SuccessState(mutableListOf()))
        }
    }

    companion object {
        private const val TAG = "SearchViewModel"
    }
}