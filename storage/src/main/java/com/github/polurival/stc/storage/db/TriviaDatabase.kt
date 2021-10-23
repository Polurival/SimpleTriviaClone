package com.github.polurival.stc.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.polurival.stc.storageapi.data.TriviaModelEntity

/**
 *
 *
 * @author Юрий Польщиков on 23.10.2021
 */
@Database(entities = [TriviaModelEntity::class], version = 1)
@TypeConverters(IncorrectAnswersTypeConverter::class)
abstract class TriviaDatabase : RoomDatabase() {
    abstract fun triviaDao(): TriviaDaoImpl
}
