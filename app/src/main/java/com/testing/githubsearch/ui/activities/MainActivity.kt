package com.testing.githubsearch.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding4.widget.textChanges
import com.testing.githubsearch.R
import com.testing.githubsearch.databinding.ActivityMainBinding
import com.testing.githubsearch.ui.adapters.SearchAdapter
import com.testing.githubsearch.ui.decorators.SpaceDecorator
import com.testing.githubsearch.util.ErrorState
import com.testing.githubsearch.util.LoadingState
import com.testing.githubsearch.util.SuccessState
import com.testing.githubsearch.viewmodel.SearchViewModel
import dagger.android.AndroidInjection
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var disposable: Disposable
    private lateinit var viewModel: SearchViewModel
    private val searchAdapter = SearchAdapter(mutableListOf())

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewModel = ViewModelProvider(this.viewModelStore, viewModelFactory)[SearchViewModel::class.java]
        setupUI()
        setupSearchObserver()
    }

    private fun setupSearchObserver() {
        viewModel.searchResultObservable.observe(this, { result ->
            when (result) {
                is SuccessState -> {
                    Log.d(TAG, result.data.toString())
                    setupSuccessState()
                    searchAdapter.refreshDataset(result.data)
                }
                is ErrorState -> {
                    setupErrorState()
                }
                is LoadingState -> {
                    setupLoadingState()
                }
            }
        })
    }

    private fun setupErrorState() {
        with(viewBinding) {
            errorContainer.errorRoot.visibility = View.VISIBLE
            rvSearchResult.visibility = View.GONE
            shimmerLoader.stopShimmer()
            shimmerLoader.visibility = View.GONE
        }
    }

    private fun setupSuccessState() {
        with(viewBinding) {
            rvSearchResult.visibility = View.VISIBLE
            shimmerLoader.stopShimmer()
            shimmerLoader.visibility = View.GONE
            errorContainer.errorRoot.visibility = View.GONE
        }
    }

    private fun setupLoadingState() {
        with(viewBinding) {
            rvSearchResult.visibility = View.GONE
            errorContainer.errorRoot.visibility = View.GONE
            shimmerLoader.visibility = View.VISIBLE
            shimmerLoader.startShimmer()
        }
    }

    private fun setupUI() {
        viewBinding.rvSearchResult.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = searchAdapter
            addItemDecoration(
                SpaceDecorator(
                    this.context,
                    R.dimen.four_dp
                )
            )
        }

        disposable = viewBinding.editTextSearch.textChanges()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
            .subscribe { textEntered ->
                Log.d(TAG, "=== $textEntered ===")
                viewModel.performSearch(searchText = textEntered.toString())
            }

        viewBinding.errorContainer.btnRetry.setOnClickListener {
            viewModel.performSearch(
                searchText = viewBinding.editTextSearch.text.toString()
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val DEBOUNCE_TIME = 300L
    }
}