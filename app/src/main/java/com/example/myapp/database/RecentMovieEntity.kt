package com.example.myapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RecentMovieEntity(
    @PrimaryKey(true) val id: Int = 0,
    val name: String,
    val rating: Double,
    val photo: String
){

}