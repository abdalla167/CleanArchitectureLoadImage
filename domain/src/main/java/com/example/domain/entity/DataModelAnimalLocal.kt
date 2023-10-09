package com.example.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "animale_table")
data class DataModelAnimalLocal(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val description: String?,
    val urls: UnsplashPhoto.UnsplashPhotoUrls,
    val user: UnsplashPhoto.UnsplashUser
)