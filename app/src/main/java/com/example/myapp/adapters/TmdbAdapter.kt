package com.example.myapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp.databinding.TmdbFilmsItemsBinding
import com.example.myapp.responses.PopularFilms

class TmdbAdapter (val onclick : (Int)->Unit) : RecyclerView.Adapter<TmdbAdapter.TmdbViewHolder>(){
    private var adapterList : List<PopularFilms?> = mutableListOf()
    class TmdbViewHolder (val binding : TmdbFilmsItemsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TmdbViewHolder {
        val binding = TmdbFilmsItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TmdbViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return adapterList.size
    }

    override fun onBindViewHolder(holder: TmdbViewHolder, position: Int) {
        val binding=holder.binding
        val movie=adapterList[position]

        binding.imageHome.setOnClickListener {
            if (movie != null) {
                onclick(movie.id)
            }
        }

        Glide.with(binding.imageHome).load("https://image.tmdb.org/t/p/w500"+ movie?.posterPath).into(binding.imageHome)

    }

    fun updateList(newList: List<PopularFilms?>){
        adapterList=newList
        notifyDataSetChanged()
    }

}
