package com.mirdev.playmovieapp.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mirdev.playmovieapp.data.db.entities.FavoritesMoviesEntity
import com.mirdev.playmovieapp.databinding.ItemMoviesfavoritesBinding
import com.mirdev.playmovieapp.ui.movies.MoviesViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesMoviesViewHolder(view: View, private val viewModel: MoviesViewModel) :
    RecyclerView.ViewHolder(view) {

    private val binding = ItemMoviesfavoritesBinding.bind(view)
    fun render(result: FavoritesMoviesEntity) {


        Picasso.get().load("https://image.tmdb.org/t/p/w500/${result.poster_path}")
            .into(binding.ivPoster)


        binding.cbFavorite.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.removeFromFavorites(
                    FavoritesMoviesEntity(
                        id = result.id,
                        title = result.title,
                        poster_path = "https://image.tmdb.org/t/p/w500/${result.poster_path}",
                        isFavorite = false

                    )
                )
            }
        }
    }
}