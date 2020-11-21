package com.justjump.filmscave

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.justjump.filmscave.databinding.ActivityHomeBinding
import com.justjump.filmscave.model.MovieDbClient
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val moviesAdapter = MoviesAdapter(emptyList())
        {
            Toast
                .makeText(this@HomeActivity, it.title, Toast.LENGTH_SHORT)
                .show()
        }
        binding.ListFilms.adapter = moviesAdapter

        lifecycleScope.launch {
            val apiKey = getString(R.string.api_key)
            val listPopularMovies = MovieDbClient.service!!.listPopularMovies(apiKey)
            moviesAdapter.movies = listPopularMovies.movieDbs
            moviesAdapter.notifyDataSetChanged()
        }
    }
}