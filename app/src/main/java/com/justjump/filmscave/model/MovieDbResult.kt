package com.justjump.filmscave.model

import com.google.gson.annotations.SerializedName

data class MovieDbResult(
    val page: Int,
    @SerializedName("results")
    val movieDbs: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)