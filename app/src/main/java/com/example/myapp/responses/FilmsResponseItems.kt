package com.example.myapp.responses


import com.google.gson.annotations.SerializedName

data class FilmsResponseItems(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val popularFilms: List<PopularFilms>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)