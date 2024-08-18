package com.example.marvelgateway.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String
)
