package com.jashwant.movieapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitBuilder {
    val BASE_URL = "https://www.whats-on-netflix.com/"

    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getapiService():ApiService= getRetrofit().create(ApiService::class.java)


}