package com.example.mealzapplication.di

import com.example.domain.repo.AnimalesRepo
import com.example.domain.useCase.GetAnimales
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideUseCase(mealsRepo: AnimalesRepo):GetAnimales
    {
        return GetAnimales(mealsRepo)
    }


}