package com.example.data.remot.local

import android.content.ClipData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entity.DataModelAnimalLocal

@Dao
interface DataBaseDao {

    @Query("SELECT * FROM animale_table ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getPagedList(limit: Int, offset: Int): List<DataModelAnimalLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DataModelAnimalLocal): Long
}