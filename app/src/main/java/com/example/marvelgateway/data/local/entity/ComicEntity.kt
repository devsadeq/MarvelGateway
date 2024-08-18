package com.example.marvelgateway.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comics")
data class ComicEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
)
