package com.example.myapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp.database.RecentMovieEntity
import com.example.myapp.databinding.RecentWatchedBinding
import com.example.myapp.responses.RecentMoviesResponseItems

class RecentMoviesAdapter (val onImageClicked:(RecentMovieEntity) ->Unit) : RecyclerView.Adapter<RecentMoviesAdapter.RecentMoviesViewHolder>() {
    private var recentmoviesadapterLists : List<RecentMovieEntity> = mutableListOf()
    class RecentMoviesViewHolder (val binding: RecentWatchedBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentMoviesViewHolder {
        val binding=RecentWatchedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecentMoviesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return recentmoviesadapterLists.size
    }


    override fun onBindViewHolder(holder: RecentMoviesViewHolder, position: Int) {
        val binding=holder.binding
        val recentMoviesResponse = recentmoviesadapterLists[position]

        Glide.with(binding.recentprofile.context).
        load("https://image.tmdb.org/t/p/w500" + recentMoviesResponse.photo).
        into(binding.recentprofile)

        binding.recentprofile.setOnClickListener {
            onImageClicked(recentMoviesResponse)
        }

//        val profileRating= recentMoviesResponse..div(2)
//        binding.ratingBarprofile.rating=profileRating.toFloat()
//        binding.ratingBarprofile.numStars= profileRating.toInt()+1

    }
    fun recentupdateList (recentnewList: List<RecentMovieEntity>){
        recentmoviesadapterLists= recentnewList
        notifyDataSetChanged()
    }




}