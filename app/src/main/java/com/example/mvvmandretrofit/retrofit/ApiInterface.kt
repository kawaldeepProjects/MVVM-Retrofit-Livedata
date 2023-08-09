package com.example.mvvmandretrofit.retrofit

import com.example.mvvmandretrofit.beans.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("popular?")
    fun getPopularMovies(@Query("api_key") api_key : String) : Call<Movies>

}