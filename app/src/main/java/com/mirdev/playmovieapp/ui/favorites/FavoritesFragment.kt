package com.mirdev.playmovieapp.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.mirdev.playmovieapp.R
import com.mirdev.playmovieapp.databinding.FragmentFavoritesBinding
import com.mirdev.playmovieapp.ui.adapter.FavoritesMoviesAdapter
import com.mirdev.playmovieapp.ui.movies.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private val moviesViewModel by viewModels<MoviesViewModel>()
    private lateinit var favoritesAdapter: FavoritesMoviesAdapter
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()

    }

    private fun initUI() {
        initList()
        initState()
    }


    private fun initState() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                moviesViewModel.favoritesMovies.collect { favoritesList ->

                    favoritesAdapter.updateList(favoritesList)

                }
            }
        }
    }

    private fun initList() {
        favoritesAdapter = FavoritesMoviesAdapter(mutableListOf(), moviesViewModel)

        binding.rvFavoritesMovies.layoutManager = GridLayoutManager(context, 2)
        binding.rvFavoritesMovies.adapter = favoritesAdapter


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}