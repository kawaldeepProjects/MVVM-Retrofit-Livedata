package com.example.mvvmandretrofit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmandretrofit.beans.Desc
import com.example.mvvmandretrofit.databinding.MovieLayoutBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movieList = ArrayList<Desc>()

    class ViewHolder(val binding : MovieLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500"+movieList[position].poster_path)
            .into(holder.binding.movieImage)
        holder.binding.movieName.text = movieList[position].title

        holder.binding.movieImage.setOnClickListener {

        }
    }
    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setMovieList(movieList : List<Desc>){
        this.movieList = movieList as ArrayList<Desc>
        notifyDataSetChanged()
    }

}