package com.justjump.filmscave

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.justjump.filmscave.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //load the theme
        setTheme(R.style.Theme_FilmsCave)

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}