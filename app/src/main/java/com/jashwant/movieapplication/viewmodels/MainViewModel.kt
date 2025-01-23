package com.jashwant.movieapplication.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jashwant.movieapplication.MovieRepository
import com.jashwant.movieapplication.RetrofitBuilder
import com.jashwant.movieapplication.TAG
import com.jashwant.movieapplication.models.MovieListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MovieRepository):ViewModel(){

    private val _movielist =MutableLiveData<List<MovieListItem>>()
    val movieList:LiveData<List<MovieListItem>> = _movielist

    init{
        var apiService=RetrofitBuilder.getapiService()
        viewModelScope.launch(Dispatchers.IO) {
            var data=apiService.getmovieslist()
            _movielist.postValue(data.body())
            Log.d(TAG, "movielist item: ${data.body()}")
        }
    }

    class Factory(private val repository: MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}