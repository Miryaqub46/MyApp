package com.example.myapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp.databinding.UpcomingFilmsItemsBinding
import com.example.myapp.responses.UpcomingFilmsResponseItems

class TmdbGroupsAdapter(
    val onClick:()->Unit
) : RecyclerView.Adapter<TmdbGroupsAdapter.TmdbGroupsViewHolder>() {
    var adaptergroupsList : MutableList<List<UpcomingFilmsResponseItems?>> = mutableListOf()
    class TmdbGroupsViewHolder ( val binding: UpcomingFilmsItemsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TmdbGroupsViewHolder {
        val binding= UpcomingFilmsItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TmdbGroupsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return adaptergroupsList.size
    }

    override fun onBindViewHolder(holder: TmdbGroupsViewHolder, position: Int) {
        val binding=holder.binding
        binding.root.setOnClickListener { onClick }
        val upcomingmovie=adaptergroupsList[position]
        binding.textviewfilmname.text= upcomingmovie[0]?.originalTitle
        binding.groupsname.text=upcomingmovie[0]?.originalTitle
        binding.randomnumber.text= upcomingmovie[0]?.voteCount.toString()





        Glide.with(binding.imageprofiles.context).
        load("https://i.pravatar.cc/150?img=$position").into(binding.imageprofiles)

        Glide.with(binding.upcominghomepage.context).
        load("https://image.tmdb.org/t/p/w500" + upcomingmovie[0]?.posterPath).into(binding.upcominghomepage )

        Glide.with(binding.upcominghomepage2.context).load("https://image.tmdb.org/t/p/w500"+upcomingmovie[1]?.posterPath)
            .into(binding.upcominghomepage2)

        Glide.with(binding.upcominghomepage3.context).
        load("https://image.tmdb.org/t/p/w500" + upcomingmovie[2]?.posterPath).into(binding.upcominghomepage3)

        Glide.with(binding.upcominghomepage4.context).
        load("https://image.tmdb.org/t/p/w500" + upcomingmovie[3]?.posterPath).into(binding.upcominghomepage4)


    }
    fun updategroupsList (newgroupsList: List<List<UpcomingFilmsResponseItems?>>){
        if ( adaptergroupsList!=newgroupsList){
            adaptergroupsList= newgroupsList.toMutableList()
        }
        notifyDataSetChanged()
    }


}