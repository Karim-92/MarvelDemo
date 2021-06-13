package com.karim.marveldemo.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karim.marveldemo.data.CharacterData

@Database(entities = [CharacterData::class], version = 1, exportSchema = false)
abstract class CharactersDb : RoomDatabase() {

    abstract fun charactersDao() : CharactersDao
}