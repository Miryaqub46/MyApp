package com.example.myapp.responses


import com.google.gson.annotations.SerializedName

data class RatingResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<RatingResponseItems>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)