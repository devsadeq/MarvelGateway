package com.example.marvelgateway.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "series")
data class SeriesEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String
)
