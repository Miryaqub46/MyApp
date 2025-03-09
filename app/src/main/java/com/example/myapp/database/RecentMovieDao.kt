package com.example.myapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecentMovieDao {

    @Query ("SELECT*from RecentMovieEntity")
    suspend fun getMovies() : List<RecentMovieEntity>
    @Insert
    suspend fun addMovies(movie :RecentMovieEntity)
}