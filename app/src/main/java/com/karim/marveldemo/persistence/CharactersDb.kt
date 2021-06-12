package com.karim.marveldemo.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karim.marveldemo.data.CharactersData

@Database(entities = [CharactersData::class], version = 1, exportSchema = true)
abstract class CharactersDb : RoomDatabase() {

    abstract fun charactersDao() : CharactersDao
}