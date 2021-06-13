package com.karim.marveldemo.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.karim.marveldemo.data.MarvelCharacter

@Database(entities = [MarvelCharacter::class], version = 1, exportSchema = false)
@TypeConverters(MarvelTypeConverters::class)
abstract class CharactersDb : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
}