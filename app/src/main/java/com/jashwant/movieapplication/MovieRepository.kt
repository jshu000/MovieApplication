package com.jashwant.movieapplication

import com.jashwant.movieapplication.models.MovieListItem
import retrofit2.Response

class MovieRepository(private val apiService: ApiService) {




    suspend fun getmovielist(): List<MovieListItem>? {
        return apiService.getmovieslist().body()
    }
}