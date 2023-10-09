package com.example.mealzapplication.di

import com.example.data.broadCast.ConnectivityBroadcastReceiver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object BroadcastModule {

    @Provides
    fun provideConnectivityBroadcastReceiver(): ConnectivityBroadcastReceiver {
        return ConnectivityBroadcastReceiver()
    }

}