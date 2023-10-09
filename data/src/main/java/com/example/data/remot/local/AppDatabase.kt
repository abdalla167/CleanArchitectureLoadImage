package com.example.data.remot.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.entity.DataModelAnimalLocal

@Database(entities = [DataModelAnimalLocal::class], version = 1, exportSchema = false)
abstract class AppDatabase :RoomDatabase()
{

    abstract fun ModelDao (): DataBaseDao

}