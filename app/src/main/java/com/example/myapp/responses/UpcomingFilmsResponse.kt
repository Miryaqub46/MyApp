package com.example.myapp.responses


import com.google.gson.annotations.SerializedName

data class UpcomingFilmsResponse(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val upcomingFilms: List<UpcomingFilmsResponseItems>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)