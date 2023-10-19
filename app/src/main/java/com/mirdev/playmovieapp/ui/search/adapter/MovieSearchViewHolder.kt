package com.mirdev.playmovieapp.ui.search.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mirdev.playmovieapp.databinding.ItemSearchmovieBinding
import com.mirdev.playmovieapp.domain.model.movieSearchModel.MovieSearchModel
import com.squareup.picasso.Picasso

class MovieSearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSearchmovieBinding.bind(view)

    fun render(result: MovieSearchModel, onItemSelected: (Int) -> Unit) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + result.poster)
            .into(binding.ivPoster)
        itemView.setOnClickListener { onItemSelected(result.id) }
    }
}