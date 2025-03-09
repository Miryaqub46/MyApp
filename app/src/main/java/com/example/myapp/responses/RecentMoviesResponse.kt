package com.example.myapp.responses


import com.google.gson.annotations.SerializedName

data class RecentMoviesResponse(
    @SerializedName("dates")
    val dates: DatesX,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<RecentMoviesResponseItems>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)