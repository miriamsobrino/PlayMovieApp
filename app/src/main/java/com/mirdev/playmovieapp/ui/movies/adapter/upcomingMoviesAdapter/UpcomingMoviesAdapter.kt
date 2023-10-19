package com.mirdev.playmovieapp.ui.movies.adapter.upcomingMoviesAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mirdev.playmovieapp.R
import com.mirdev.playmovieapp.domain.model.homeMoviesModel.HomeMoviesModel
import com.mirdev.playmovieapp.ui.movies.MoviesViewModel

class UpcomingMoviesAdapter(
    private var moviesList: List<HomeMoviesModel> = emptyList(),
    private val viewModel: MoviesViewModel, private val onItemSelected: (Int) -> Unit
) : RecyclerView.Adapter<UpcomingMoviesViewHolder>() {


    fun updateList(moviesList: List<HomeMoviesModel>) {
        this.moviesList = moviesList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UpcomingMoviesViewHolder(
            layoutInflater.inflate(
                R.layout.item_upcomingmovies,
                parent,
                false
            ), viewModel
        )
    }

    override fun getItemCount() = moviesList.size


    override fun onBindViewHolder(holder: UpcomingMoviesViewHolder, position: Int) {
        holder.render(moviesList[position], onItemSelected)
    }
}