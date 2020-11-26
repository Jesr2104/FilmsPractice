package com.justjump.filmscave

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.justjump.filmscave.databinding.ActivityHomeBinding
import com.justjump.filmscave.model.MovieDbClient
import kotlinx.coroutines.launch


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null){
            val data = extras.getString("EmailUser")

            //we save the credentials of the login
            val sPreferences = getSharedPreferences(
                getString(R.string.prefs_login),
                Context.MODE_PRIVATE
            ).edit()
            sPreferences.putString("email",data)
            sPreferences.apply()

            setUp(data!!, binding)
        }

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

    fun setUp(email: String, binding: ActivityHomeBinding)
    {
        binding.User.text = email
        binding.Logout.setOnClickListener(){
            FirebaseAuth.getInstance().signOut()

            //we save the credentials of the login
            val sPreferences = getSharedPreferences(
                getString(R.string.prefs_login),
                Context.MODE_PRIVATE
            ).edit()
            sPreferences.clear()
            sPreferences.apply()

            onBackPressed()
        }
    }
}