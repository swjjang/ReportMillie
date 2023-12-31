package com.swjjang7.reportmillie.repository.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.swjjang7.reportmillie.repository.local.entity.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM Article")
    fun findAll(): Flow<List<Article>>

    @Query("SELECT * FROM Article WHERE read = 1")
    fun findAllReadList(): Flow<List<Article>>

    @Upsert
    fun upsertArticleList(list: List<Article>)

    @Update
    fun updateArticle(article: Article)
}