package com.mirdev.playmovieapp.ui.movies.adapter.popularMoviesAdapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mirdev.playmovieapp.data.db.entities.FavoritesMoviesEntity
import com.mirdev.playmovieapp.databinding.ItemPopularmoviesBinding
import com.mirdev.playmovieapp.domain.model.homeMoviesModel.HomeMoviesModel
import com.mirdev.playmovieapp.ui.movies.MoviesViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopularMoviesViewHolder(view: View, private val viewModel: MoviesViewModel) :
    RecyclerView.ViewHolder(view) {

    private val binding = ItemPopularmoviesBinding.bind(view)

    fun render(result: HomeMoviesModel, onItemSelected: (Int) -> Unit) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + result.poster).into(binding.ivPoster)
        itemView.setOnClickListener { onItemSelected(result.id) }
        val movieId = result.id
        binding.cbFavorite.isChecked = result.isFavorite

        binding.cbFavorite.setOnCheckedChangeListener { _, isChecked ->

            CoroutineScope(Dispatchers.IO).launch {
                saveCheckBoxState(movieId, isChecked)

                if (isChecked) {

                    viewModel.addToFavorites(
                        FavoritesMoviesEntity(
                            id = result.id,
                            title = result.title,
                            poster_path = "https://image.tmdb.org/t/p/w500/${result.poster}",
                            isFavorite = result.isFavorite

                        )
                    )
                } else {
                    viewModel.removeFromFavorites(
                        FavoritesMoviesEntity(
                            id = result.id,
                            title = result.title,
                            poster_path = "https://image.tmdb.org/t/p/w500/${result.poster}",
                            isFavorite = result.isFavorite
                        )
                    )
                }

            }
        }
    }

    private fun saveCheckBoxState(movieId: Int, isChecked: Boolean) {
        val sharedPreferences =
            itemView.context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("favorite_$movieId", isChecked).apply()
    }


}