package com.example.marvelgateway.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelgateway.data.local.entity.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(character: List<CharacterEntity>)

    @Query(
        """
        SELECT * FROM characters
        WHERE (:nameStartsWith IS NULL OR name LIKE :nameStartsWith || '%')
    """
    )
    suspend fun getAllCharacters(
        nameStartsWith: String?,
    ): List<CharacterEntity>

    @Query("SELECT * FROM characters WHERE id = :characterId")
    suspend fun getCharacterById(characterId: Int): CharacterEntity

    @Query("SELECT * FROM characters WHERE name = :characterName")
    suspend fun getCharacterByName(characterName: String): CharacterEntity
}