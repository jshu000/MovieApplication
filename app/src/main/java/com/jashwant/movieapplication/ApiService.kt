package com.jashwant.movieapplication

import com.jashwant.movieapplication.models.MovieList
import com.jashwant.movieapplication.models.MovieListItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    //https://www.whats-on-netflix.com/wp-content/plugins/whats-on-netflix/json/alldocs.json?_=1700718031139
    @GET("wp-content/plugins/whats-on-netflix/json/alldocs.json?_=1700718031139")
    suspend fun getmovieslist():Response<List<MovieListItem>>
}