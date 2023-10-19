package com.mirdev.playmovieapp.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mirdev.playmovieapp.R
import com.mirdev.playmovieapp.domain.model.movieSearchModel.MovieSearchModel

class MovieSearchAdapter(
    private var moviesList: List<MovieSearchModel> = emptyList(),
    private val onItemSelected: (Int) -> Unit
) : RecyclerView.Adapter<MovieSearchViewHolder>() {

    fun updateList(moviesList: List<MovieSearchModel>) {
        this.moviesList = moviesList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieSearchViewHolder(
            layoutInflater.inflate(
                R.layout.item_searchmovie,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = moviesList.size

    override fun onBindViewHolder(holder: MovieSearchViewHolder, position: Int) {
        holder.render(moviesList[position], onItemSelected)
    }
}