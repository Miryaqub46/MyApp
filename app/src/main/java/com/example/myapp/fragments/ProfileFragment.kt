package com.example.myapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.myapp.R
import com.example.myapp.adapters.RecentCommentAdapter
import com.example.myapp.adapters.RecentMoviesAdapter
import com.example.myapp.database.MovieDataBase
import com.example.myapp.database.RecentMovieEntity
import com.example.myapp.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    //private val viewModel : ProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moviedatabase= Room.
        databaseBuilder(requireContext(),MovieDataBase::class.java,"data").build()
        val recentcommentmoviedao=moviedatabase.getRecentMovieDao()
        val recentmoviedao=moviedatabase.getRecentMovieDao()
        val recentAdapters=RecentMoviesAdapter({
            lifecycleScope.launch {
                recentcommentmoviedao.addMovies(it)
            }
        })
        binding.profilerw.adapter=recentAdapters


        binding.recentcommentrw.adapter=recentAdapters

        lifecycleScope.launch {
            recentAdapters.recentupdateList(recentcommentmoviedao.getMovies())
        }

        lifecycleScope.launch {
            recentAdapters.recentupdateList(recentmoviedao.getMovies())
        }
        binding.kyranprofile.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_moviePageFragment)
        }



    }
}
//

//
//        viewModel.recentLiveData.observe(viewLifecycleOwner){recentresponse->
//            if (recentresponse != null) {
//                recentAdapters.recentupdateList(recentresponse)
//            }
//        }
//
//        val recentcommentAdapter=RecentCommentAdapter()
//        binding.recentcommentrw.adapter=recentcommentAdapter
//
//        viewModel.recentcommentLiveData.observe(viewLifecycleOwner) { recentcommentResponse ->
//            if (recentcommentResponse != null) {
//                recentcommentAdapter.updaterecentcommentList(recentcommentResponse)
//            }
//        }


//class ProfileViewModel : ViewModel() {
//    private val recentRetrofitClient = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
//        .addConverterFactory(GsonConverterFactory.create()).build()
//
//    val recentapi = recentRetrofitClient.create(ProfileFilms::class.java)
//    val recentLiveData = MutableLiveData<List<RecentMoviesResponseItems?>?>(null)
//
//    val recentcommentapi = recentRetrofitClient.create(ProfileFilms::class.java)
//    val recentcommentLiveData = MutableLiveData<List<RecentMoviesResponseItems>>(null)
//
//    init {
//        getRecent()
//    }
//
//    private fun getRecent() {
//        viewModelScope.launch {
//            val recentresponse = recentapi.getRecentMovies().results
//            recentLiveData.postValue(recentresponse)
//
//            val recentcommentResponse = recentcommentapi.getRecentCommentMovies().results
//            recentcommentLiveData.postValue(recentcommentResponse)
//
//        }
//    }
//}