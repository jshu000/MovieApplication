package com.jashwant.movieapplication

class MovieRepository(private val apiService: ApiService) {




    suspend fun getmovielist(){
        apiService.getmovieslist()
    }
}