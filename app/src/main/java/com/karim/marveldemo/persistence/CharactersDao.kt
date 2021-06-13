package com.karim.marveldemo.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karim.marveldemo.data.CharacterData

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharactersList(characters : List<CharacterData>)

    @Query("SELECT * FROM CharacterData")
    fun getAllCharactersFromDb () : List<CharacterData>


}