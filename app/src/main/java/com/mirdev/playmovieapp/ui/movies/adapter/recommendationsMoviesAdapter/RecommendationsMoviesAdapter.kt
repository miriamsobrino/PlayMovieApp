package com.mirdev.playmovieapp.ui.movies.adapter.recommendationsMoviesAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mirdev.playmovieapp.R
import com.mirdev.playmovieapp.domain.model.homeMoviesModel.HomeMoviesModel
import com.mirdev.playmovieapp.ui.movies.MoviesViewModel

class RecommendationsMoviesAdapter(
    private var moviesList: List<HomeMoviesModel> = emptyList(),
    private val viewModel: MoviesViewModel,
    private val onItemSelected: (Int) -> Unit
) : RecyclerView.Adapter<RecommendationsMoviesViewHolder>() {

    fun updateList(moviesList: List<HomeMoviesModel>) {
        this.moviesList = moviesList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendationsMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecommendationsMoviesViewHolder(
            layoutInflater.inflate(
                R.layout.item_recommendationsmovies,
                parent,
                false
            ), viewModel
        )
    }

    override fun getItemCount() = moviesList.size

    override fun onBindViewHolder(holder: RecommendationsMoviesViewHolder, position: Int) {
        holder.render(moviesList[position], onItemSelected)
    }
}