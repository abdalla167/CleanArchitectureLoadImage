package com.example.mealzapplication.di

import android.app.Application
import androidx.room.Room
import com.example.data.remot.local.AppDatabase
import com.example.data.remot.local.DataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideModelDao(appDatabase: AppDatabase): DataBaseDao {
        return appDatabase.ModelDao()
    }



    @Singleton
    @Provides
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "AnimaleRoom"
        ).build()
    }
}