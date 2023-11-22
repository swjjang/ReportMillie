package com.swjjang7.reportmillie.di

import android.content.Context
import androidx.room.Room
import com.swjjang7.reportmillie.repository.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, "reportmillie.db")
        .build()

    @Singleton
    @Provides
    fun provideArticleDao(appDatabase: AppDatabase) = appDatabase.articleDao()
}