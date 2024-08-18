package com.example.marvelgateway.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelgateway.data.local.entity.ComicEntity

@Dao
interface ComicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComics(comics: List<ComicEntity>)

    @Query("SELECT * FROM comics")
    suspend fun getAllComics(): List<ComicEntity>
}