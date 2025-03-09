package com.example.myapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp.databinding.RecentcommentWatchedBinding
import com.example.myapp.responses.RecentMoviesResponseItems

class RecentCommentAdapter : RecyclerView.Adapter<RecentCommentAdapter.RecentCommentViewHolder>() {
    var recentcommentLists : List<RecentMoviesResponseItems> = emptyList()
    class RecentCommentViewHolder (val binding: RecentcommentWatchedBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentCommentViewHolder {
        val binding=RecentcommentWatchedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecentCommentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return recentcommentLists.size
    }

    override fun onBindViewHolder(holder: RecentCommentViewHolder, position: Int) {
        val binding=holder.binding
        val recentcommentmovies=recentcommentLists[position]

        binding.recentcommentbyname.text=recentcommentmovies.originalTitle
        binding.recentcommentname.text=recentcommentmovies.originalTitle

        Glide.with(binding.recentorginalimage.context).
        load("https://image.tmdb.org/t/p/w500" + recentcommentmovies.posterPath).
        into(binding.recentorginalimage)

        Glide.with(binding.recentprofileimage.context).load("https://i.pravatar.cc/150?img=$position")
            .into(binding.recentprofileimage)

        val recentcommentRating=recentcommentmovies.voteAverage/2
        binding.recentratingBar.rating=recentcommentRating.toFloat()
        binding.recentratingBar.numStars=recentcommentRating.toInt()+1
    }
    fun updaterecentcommentList ( newrecentcommentList : List<RecentMoviesResponseItems>){
        recentcommentLists=newrecentcommentList
        notifyDataSetChanged()
    }


}