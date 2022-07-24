package com.shahriyor.android_imperativ_graphql.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shahriyor.android_graphql.PopularMoviesQuery.*
import com.shahriyor.android_imperativ_graphql.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
    get() = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
    get() = _error

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: MutableLiveData<List<Movie>>
    get() = _movies

    val states = MutableLiveData<ArrayList<Any>>()




    fun apiMovies() {
        _isLoading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.popularMovies()
            withContext(Dispatchers.Main){
                if (!response.hasErrors()) {
                    _movies.value = response.data?.popularMovies?.movies!!
                    _isLoading.value = false
                } else {
                    _error.value = response.errors!![0].toString()
                    _isLoading.value = false
                }
            }
        }
    }








}