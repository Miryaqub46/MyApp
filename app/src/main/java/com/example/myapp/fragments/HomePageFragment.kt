package com.example.myapp.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Layout.Directions
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.myapp.R
import com.example.myapp.adapters.RatingCommentAdapter
import com.example.myapp.adapters.RecentMoviesAdapter
import com.example.myapp.adapters.TmdbAdapter
import com.example.myapp.adapters.TmdbGroupsAdapter
import com.example.myapp.database.MovieDataBase
import com.example.myapp.database.RecentMovieEntity
import com.example.myapp.databinding.FragmentHomePageBinding
import com.example.myapp.responses.FilmsResponseItems
import com.example.myapp.responses.PopularFilms
import com.example.myapp.responses.RatingResponse
import com.example.myapp.responses.RatingResponseItems
import com.example.myapp.responses.UpcomingFilmsResponse
import com.example.myapp.responses.UpcomingFilmsResponseItems
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    private val viewModel: MyViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moviedatabase= Room.databaseBuilder(requireContext(), MovieDataBase::class.java,"data").build()
        val recentmoviedao=moviedatabase.getRecentMovieDao()
        val profilephoto=requireActivity().findViewById<ImageView>(R.id.imageHome)

        val recentcoomentmoviedao=moviedatabase.getRecentMovieDao()
        val commentphoto=requireActivity().findViewById<ImageView>(R.id.ratingorginalphoto)




        val tmdbAdapter = TmdbAdapter({ findNavController().navigate(HomePageFragmentDirections.actionHomePageFragmentToMoviePageFragment(it))})
        binding.rvhomepage.adapter = tmdbAdapter
        viewModel.FilmlerLiveData.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                tmdbAdapter.updateList(response)
            }
        }

        val tmdbgroupsAdapter = TmdbGroupsAdapter(onClick = {
            Toast.makeText(
                requireContext(),
                "jdfjnvdjnfvjnkdfv",
                Toast.LENGTH_SHORT
            ).show()})
        binding.rvupcoming.adapter = tmdbgroupsAdapter
        viewModel.UpcomingFilmsLiveData.observe(viewLifecycleOwner) { upcomingResponse ->
            if (upcomingResponse != null) {
                tmdbgroupsAdapter.updategroupsList(upcomingResponse)
            }

        }

        val ratingAdapter = RatingCommentAdapter(

            {
                if(it!=null){
                    lifecycleScope.launch {
                        recentmoviedao.addMovies(RecentMovieEntity(name="yaqub",rating=3.6, photo =it))
                    }
                    findNavController().navigate(HomePageFragmentDirections.actionHomePageFragmentToProfileFragment(0,it))

                }
            }
        )
        binding.rvrating.adapter = ratingAdapter
        viewModel.RatingLiveData.observe(viewLifecycleOwner) { ratingResponse ->
            if (ratingResponse != null) {
                ratingAdapter.updateratingList(ratingResponse)
            }

        }
        val homebuttonnavigation =
            requireActivity().findViewById<ConstraintLayout>(R.id.homebuttonnavigation)
        homebuttonnavigation.setOnClickListener {
            homebuttonnavigation.background = ColorDrawable(Color.RED)
            findNavController().navigate(R.id.homePageFragment)
        }
        val logoutbuttonnavigation =
            requireActivity().findViewById<ConstraintLayout>(R.id.logoutbuttonnavigation)
        logoutbuttonnavigation.setOnClickListener {
            logoutbuttonnavigation.background = ColorDrawable(Color.BLUE)
            findNavController().navigate(R.id.action_homePageFragment_to_logInFragment)
        }

        val likesbuttonnavigation =
            requireActivity().findViewById<ConstraintLayout>(R.id.likesbuttonnavigation)
        likesbuttonnavigation.setOnClickListener {
            likesbuttonnavigation.background = ColorDrawable(Color.CYAN)
            findNavController().navigate(R.id.action_homePageFragment_to_profileFragment)
        }

    }


}

interface Films {
    @GET("movie/popular?api_key=fe044c2393ed2e6aa76ce1d3c296e5dc")
    suspend fun getFilms(): FilmsResponseItems?

    @GET("movie/upcoming?api_key=fe044c2393ed2e6aa76ce1d3c296e5dc")
    suspend fun getUpcomingFilms(): UpcomingFilmsResponse

    @GET("movie/top_rated?api_key=fe044c2393ed2e6aa76ce1d3c296e5dc")
    suspend fun getRatingFilms(): RatingResponse
}

class MyViewModel : ViewModel() {


    private val retrofitClient = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val api = retrofitClient.create(Films::class.java)
    val FilmlerLiveData = MutableLiveData<List<PopularFilms?>?>(null)

    val upcomingapi = retrofitClient.create(Films::class.java)
    val UpcomingFilmsLiveData = MutableLiveData<List<List<UpcomingFilmsResponseItems?>>>(null)

    val ratingapi = retrofitClient.create(Films::class.java)
    val RatingLiveData = MutableLiveData<List<RatingResponseItems?>?>(null)


    init {
        getMovies()
    }


    private fun getMovies() {
        viewModelScope.launch {
            val response = api.getFilms()?.popularFilms
            FilmlerLiveData.postValue(response)

            //upcoming
            val upcomingResponse = api.getUpcomingFilms()
            val upcomingMovies = upcomingResponse.upcomingFilms.orEmpty()
            val netice: MutableList<List<UpcomingFilmsResponseItems>> = mutableListOf()
            val upcomingFilmsayi = upcomingMovies.size
            val qrupSayi = upcomingFilmsayi / 4
            repeat(qrupSayi) { qrupNomresi ->
                netice.add(upcomingMovies.subList(qrupNomresi * 4, qrupNomresi * 4 + 4))
            }
            UpcomingFilmsLiveData.postValue(netice)
            //////////

            /////rating
            val ratingResponse = ratingapi.getRatingFilms().results
            RatingLiveData.postValue(ratingResponse)

        }


    }

}


