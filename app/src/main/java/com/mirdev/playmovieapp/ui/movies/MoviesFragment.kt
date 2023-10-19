package com.mirdev.playmovieapp.ui.movies

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirdev.playmovieapp.databinding.FragmentMoviesBinding
import com.mirdev.playmovieapp.ui.movies.adapter.popularMoviesAdapter.PopularMoviesAdapter
import com.mirdev.playmovieapp.ui.movies.adapter.recommendationsMoviesAdapter.RecommendationsMoviesAdapter
import com.mirdev.playmovieapp.ui.movies.adapter.upcomingMoviesAdapter.UpcomingMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val moviesViewModel by viewModels<MoviesViewModel>()
    private lateinit var upcomingMoviesAdapter: UpcomingMoviesAdapter
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var recommendationsMoviesAdapter: RecommendationsMoviesAdapter
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()

    }

    private fun initUI() {
        binding.btnES.isActivated = true
        initListUpcomingMovies()
        initListPopularMovies()
        initListRecommendationsMovies()
        initState()
        initListeners()
    }


    private fun initListUpcomingMovies() {
        upcomingMoviesAdapter =
            UpcomingMoviesAdapter(emptyList(), moviesViewModel, onItemSelected = { movieId ->
                val action =
                    MoviesFragmentDirections.actionMoviesFragmentToDetailMovieActivity(movieId)
                findNavController().navigate(action)
            })
        binding.rvUpcomingMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = upcomingMoviesAdapter
        }
    }


    private fun initListPopularMovies() {
        popularMoviesAdapter =
            PopularMoviesAdapter(emptyList(), moviesViewModel, onItemSelected = { movieId ->
                val action =
                    MoviesFragmentDirections.actionMoviesFragmentToDetailMovieActivity(movieId)
                findNavController().navigate(action)
            })
        binding.rvPopularMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularMoviesAdapter
        }
    }

    private fun initListRecommendationsMovies() {
        recommendationsMoviesAdapter =
            RecommendationsMoviesAdapter(emptyList(), moviesViewModel, onItemSelected = { movieId ->
                val action =
                    MoviesFragmentDirections.actionMoviesFragmentToDetailMovieActivity(movieId)
                findNavController().navigate(action)
            })
        binding.rvRecommendationMovies.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = recommendationsMoviesAdapter
        }
    }

    private fun initState() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                moviesViewModel.upcomingMovies.onEach { upcomingMovies ->
                    upcomingMoviesAdapter.updateList(upcomingMovies)
                }
                    .launchIn(this)

                moviesViewModel.popularMovies.onEach { popularMovies ->
                    popularMoviesAdapter.updateList(popularMovies)
                }
                    .launchIn(this)

                moviesViewModel.recommendationsMovies.onEach { recommendationsMovies ->
                    recommendationsMoviesAdapter.updateList(recommendationsMovies)
                }
                    .launchIn(this)


                moviesViewModel.loading.collect {
                    binding.pb.isVisible = it
                }
            }
        }
    }

    private fun initListeners() {
        binding.btnES.setOnClickListener {
            binding.btnES.setTextColor(Color.parseColor("#232323"))
            binding.btnEN.setTextColor(Color.parseColor("#F9FEFF"))
            binding.btnEN.isActivated = false
            binding.btnES.isActivated = true
            lifecycleScope.launch {
                val movieValue = this@MoviesFragment.moviesViewModel.getRecommendationsMoviesES(

                )
                withContext(Dispatchers.Main) { recommendationsMoviesAdapter.updateList(movieValue) }

            }
        }

        binding.btnEN.setOnClickListener {
            binding.btnES.setTextColor(Color.parseColor("#F9FEFF"))
            binding.btnEN.setTextColor(Color.parseColor("#232323"))
            binding.btnEN.isActivated = true
            binding.btnES.isActivated = false

            lifecycleScope.launch {
                val movieValue = this@MoviesFragment.moviesViewModel.getRecommendationsMoviesEN(

                )
                withContext(Dispatchers.Main) { recommendationsMoviesAdapter.updateList(movieValue) }

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}



