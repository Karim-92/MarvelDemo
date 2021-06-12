package com.karim.marveldemo.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karim.marveldemo.data.CharactersData

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharactersList(characters : List<CharactersData>)

    @Query("SELECT * FROM CharactersData")
    fun getAllCharactersFromDb () : List<CharactersData>


}