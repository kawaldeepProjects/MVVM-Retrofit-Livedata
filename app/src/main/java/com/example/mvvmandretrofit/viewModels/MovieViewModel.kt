package com.example.mvvmandretrofit.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmandretrofit.beans.Desc
import com.example.mvvmandretrofit.beans.Movies
import com.example.mvvmandretrofit.retrofit.ApiManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieViewModel : ViewModel() {

    private var movieLiveData = MutableLiveData<List<Desc>>()

    fun getPopularMovies() {

        // with coroutines
        viewModelScope.launch(Dispatchers.Main) {
            ApiManager.apiInterface.getPopularMovies("69d66957eebff9666ea46bd464773cf0")
                .enqueue(object : Callback<Movies> {
                    override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                        if (response.body() != null) {
                            Log.d("asfasfas", "onResponse: "+response.body()!!.results.toString())
                            movieLiveData.value = response.body()!!.results
                        } else {
                            return
                        }
                    }

                    override fun onFailure(call: Call<Movies>, t: Throwable) {
                        Log.d("asfasfas","onFailure: "+ t.message.toString())
                    }
                })
        }

        // without coroutines
       /* ApiManager.apiInterface.getPopularMovies("69d66957eebff9666ea46bd464773cf0")
            .enqueue(object : Callback<Movies> {
                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    if (response.body() != null) {
                        Log.d("asfasfas", "onResponse: "+response.body()!!.results.toString())
                        movieLiveData.value = response.body()!!.results
                    } else {
                        return
                    }
                }

                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    Log.d("asfasfas","onFailure: "+ t.message.toString())
                }
            })*/
    }

    fun observeMovieLiveData(): LiveData<List<Desc>> {
        return movieLiveData
    }
}