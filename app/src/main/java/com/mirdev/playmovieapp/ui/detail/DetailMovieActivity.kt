package com.mirdev.playmovieapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import com.mirdev.playmovieapp.data.db.entities.FavoritesMoviesEntity
import com.mirdev.playmovieapp.databinding.ActivityDetailMovieBinding
import com.mirdev.playmovieapp.domain.model.detailMovieModel.DetailMovieModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private val detailMovieViewModel by viewModels<DetailMovieViewModel>()
    private val args: DetailMovieActivityArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        initListeners()
    }


    private fun initUI() {
        lifecycleScope.launch {
            val movieId = args.movieId
            val detailMovie = detailMovieViewModel.fetchDetailMovie(movieId, lang = "es")
            runOnUiThread { displayDetailMovieData(detailMovie) }
        }
    }

    private fun displayDetailMovieData(detailMovie: DetailMovieModel) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + detailMovie.poster)
            .into(binding.ivPosterDetail)
        binding.tvTitleDetail.text = detailMovie.title
        binding.tvLanguage.text = detailMovie.language

        val genres = detailMovie.genres.map { it.name }
        val genresText = genres.joinToString(" · ") // Une los nombres de los géneros con comas
        binding.tvGenre.text = genresText

        binding.tvOverview.text = detailMovie.overview

        parseDate(detailMovie)
        formatRate(detailMovie)

        binding.cbFavorite.setOnCheckedChangeListener { _, isChecked ->
            CoroutineScope(Dispatchers.IO).launch {
                if (isChecked) {
                    detailMovieViewModel.addToFavorites(
                        FavoritesMoviesEntity(
                            id = detailMovie.id,
                            title = detailMovie.title,
                            poster_path = "https://image.tmdb.org/t/p/w500/${detailMovie.poster}",
                            isFavorite = detailMovie.isFavorite

                        )
                    )
                } else {
                    detailMovieViewModel.removeFromFavorites(
                        FavoritesMoviesEntity(
                            id = detailMovie.id,
                            title = detailMovie.title,
                            poster_path = "https://image.tmdb.org/t/p/w500/${detailMovie.poster}",
                            isFavorite = detailMovie.isFavorite
                        )
                    )
                }

            }
        }
    }


    private fun parseDate(detailMovie: DetailMovieModel) {

        val releaseDate = detailMovie.releaseDate
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = sdf.parse(releaseDate)
        val calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        binding.tvYear.text = year.toString()
    }

    private fun formatRate(detailMovie: DetailMovieModel) {
        val decimalFormat = DecimalFormat("#.#")
        val voteAverage = decimalFormat.format(detailMovie.rate)
        binding.tvRate.text = voteAverage
    }


    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }


    }

}

