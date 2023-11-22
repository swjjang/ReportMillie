package com.swjjang7.reportmillie.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.swjjang7.reportmillie.repository.local.entity.Article

@Database(
    entities = [Article::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}