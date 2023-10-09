package com.example.mealzapplication.di

import com.example.data.remot.ApiService
import com.example.data.repo.AnimalesRepoImpl
import com.example.domain.repo.AnimalesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepoModule
{


    @Provides
    fun provideRepo (apiService: ApiService):AnimalesRepo
    {
        return AnimalesRepoImpl(apiService)
    }

}