package com.example.myapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp.databinding.RatingCommentBinding
import com.example.myapp.responses.RatingResponseItems

class RatingCommentAdapter(
    val onClick:(String?)->Unit
) : RecyclerView.Adapter<RatingCommentAdapter.RatingCommentViewHolder>() {

    private var ratingadapterList : MutableList<RatingResponseItems?> = mutableListOf()

    class RatingCommentViewHolder (val binding: RatingCommentBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingCommentViewHolder {
        val binding=RatingCommentBinding.
        inflate(LayoutInflater.from(parent.context),parent,false)
        return RatingCommentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ratingadapterList.size
    }

    override fun onBindViewHolder(holder: RatingCommentViewHolder, position: Int) {
        val binding =holder.binding
        val ratingmovie=ratingadapterList[position]

        binding.comment.setOnClickListener { onClick(ratingmovie?.posterPath) }

        binding.ratingname.text=ratingmovie?.originalTitle
        binding.comment.text=ratingmovie?.overview
        binding.ratingbyname.text=ratingmovie?.title

        Glide.with(binding.ratingorginalphoto.context).
        load("https://image.tmdb.org/t/p/w500" + ratingmovie?.posterPath).into(binding.ratingorginalphoto)

        Glide.with(binding.ratingprofilephoto.context).
        load("https://i.pravatar.cc/150?img=$position").into(binding.ratingprofilephoto)

        val rating= ratingmovie?.voteAverage?.div(2)
        if (rating != null) {
            binding.ratingBar.rating=rating.toFloat()
        }
        if (rating != null) {
            binding.ratingBar.numStars=rating.toInt()+1
        }


    }
    fun updateratingList (newratingList: List<RatingResponseItems?>){
        ratingadapterList= newratingList.toMutableList()
        notifyDataSetChanged()
    }
}