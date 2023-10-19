package com.mirdev.playmovieapp.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mirdev.playmovieapp.databinding.FragmentSearchBinding
import com.mirdev.playmovieapp.ui.search.adapter.MovieSearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var movie: String
    private lateinit var searchAdapter : MovieSearchAdapter
    private val searchFragmentViewModel by viewModels<SearchFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initList()


    }

    private fun initUI(){
        initListeners()
    }

    private fun initList() {
        searchAdapter = MovieSearchAdapter( onItemSelected ={ movieId ->


            val action = SearchFragmentDirections.actionSearchFragmentToDetailMovieActivity(movieId)
            findNavController().navigate(action)
        })
        binding.rvSearch.apply {
            layoutManager = GridLayoutManager(context,2 )
            adapter = searchAdapter
        }
    }

    private fun initListeners() {
        binding.svMovie.setOnQueryTextListener(object: SearchView.OnQueryTextListener {


            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                fetchListMovies(newText!!)
                return true
            }

        })
        }

@SuppressLint("SuspiciousIndentation")
private fun fetchListMovies(query: String){
    lifecycleScope.launch {
      val movieValue=  this@SearchFragment.searchFragmentViewModel.fetchListMovies(query)
        withContext(Dispatchers.Main){ searchAdapter.updateList(movieValue)}

    }


}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }




}