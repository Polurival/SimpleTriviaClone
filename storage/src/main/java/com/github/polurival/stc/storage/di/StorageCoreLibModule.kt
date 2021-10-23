package com.github.polurival.stc.storage.di

import android.app.Activity
import android.content.Context
import androidx.room.Room
import com.github.polurival.stc.coreapi.context.ApplicationContext
import com.github.polurival.stc.storage.PreferencesManagerImpl
import com.github.polurival.stc.storage.db.TriviaDatabase
import com.github.polurival.stc.storageapi.PreferencesManager
import com.github.polurival.stc.storageapi.data.TriviaDao
import dagger.Module
import dagger.Provides
import dagger.Reusable

/**
 *
 *
 * @author Юрий Польщиков on 12.09.2021
 */
@Module
internal object StorageCoreLibModule {

    @Provides
    @Reusable
    fun providePreferencesManager(@ApplicationContext context: Context): PreferencesManager {
        val prefs = context.getSharedPreferences("user_prefs", Activity.MODE_PRIVATE)
        return PreferencesManagerImpl(prefs)
    }

    @Provides
    @Reusable
    fun provideTriviaDatabase(@ApplicationContext context: Context): TriviaDatabase {
        return Room.databaseBuilder(
            context,
            TriviaDatabase::class.java, "trivia_db"
        ).build()
    }

    @Provides
    @Reusable
    fun provideTriviaDao(triviaDatabase: TriviaDatabase): TriviaDao {
        return triviaDatabase.triviaDao()
    }
}
