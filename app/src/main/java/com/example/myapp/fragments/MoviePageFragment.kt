package com.example.myapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapp.databinding.FragmentMoviePageBinding
import com.example.myapp.responses.MovieDetailsResponse
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class MoviePageFragment : Fragment() {

    private val viewModel: MovieviewModels by viewModels()
    val argument: MoviePageFragmentArgs by navArgs()

    private lateinit var binding: FragmentMoviePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getMoviesDetails(argument.id)

        viewModel.movieDetailsLiveData.observe(viewLifecycleOwner) { detailsResponse ->
            detailsResponse?.let {
                Glide.with(binding.root)
                    .load("https://image.tmdb.org/t/p/w500${it.backdropPath}")
                    .into(binding.imageViewBatman)

                binding.textViewBatman.text = it.originalTitle
                binding.texviewInfo.text= it.overview
            }
        }
    }
}



interface Movies {
    @GET("movie/{movie_id}?api_key=fe044c2393ed2e6aa76ce1d3c296e5dc")
    suspend fun getMoviesDetails(@Path("movie_id") movieId: Int): MovieDetailsResponse
}

class MovieviewModels : ViewModel() {

    private val retrofitClientDetails = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val detailsApi = retrofitClientDetails.create(Movies::class.java)

    val movieDetailsLiveData = MutableLiveData<MovieDetailsResponse?>()

    fun getMoviesDetails(movieId: Int) {
        viewModelScope.launch {
                val detailsResponse = detailsApi.getMoviesDetails(movieId)
                movieDetailsLiveData.postValue(detailsResponse)

        }
    }
}
