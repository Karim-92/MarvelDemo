package com.karim.marveldemo.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karim.marveldemo.data.MarvelCharacter

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharactersList(characters: List<MarvelCharacter>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: MarvelCharacter)

    @Query("SELECT * FROM MarvelCharacter WHERE name LIKE :query ORDER BY Name ASC")
    fun getQueryCharacters(query: String): List<MarvelCharacter>

    @Query("SELECT * FROM MarvelCharacter WHERE page=:page_ ORDER BY Name ASC")
    fun getAllCharactersFromDb(page_: Int): List<MarvelCharacter>

    @Query("SELECT * FROM MarvelCharacter WHERE page <= :page_ ORDER BY Name ASC")
    suspend fun getAllCharactersList(page_: Int): List<MarvelCharacter>

    @Query("SELECT * FROM MarvelCharacter WHERE id = :characterId_")
    suspend fun getCharacterData(characterId_ : Int): MarvelCharacter?

    @Query("DELETE FROM MarvelCharacter")
    suspend fun deleteAllCharacters()
}
