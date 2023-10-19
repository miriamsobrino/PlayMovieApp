package com.mirdev.playmovieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mirdev.playmovieapp.R
import com.mirdev.playmovieapp.data.db.entities.FavoritesMoviesEntity
import com.mirdev.playmovieapp.ui.movies.MoviesViewModel

class FavoritesMoviesAdapter(
    private var moviesList: MutableList<FavoritesMoviesEntity> = mutableListOf(),
    private val viewModel: MoviesViewModel
) : RecyclerView.Adapter<FavoritesMoviesViewHolder>() {

    fun updateList(moviesList: List<FavoritesMoviesEntity>) {
        this.moviesList.clear()
        this.moviesList.addAll(moviesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoritesMoviesViewHolder(
            layoutInflater.inflate(
                R.layout.item_moviesfavorites,
                parent,
                false
            ), viewModel
        )
    }

    override fun getItemCount() = moviesList.size

    override fun onBindViewHolder(holder: FavoritesMoviesViewHolder, position: Int) {
        holder.render(moviesList[position])
    }
}