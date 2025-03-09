package com.example.myapp.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase

@Database ([RecentMovieEntity :: class], version = 2)
abstract class MovieDataBase : RoomDatabase (){
    abstract fun getRecentMovieDao():RecentMovieDao
}