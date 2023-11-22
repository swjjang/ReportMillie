package com.swjjang7.reportmillie.repository

import com.swjjang7.reportmillie.repository.local.ArticleDao
import com.swjjang7.reportmillie.repository.local.entity.Article
import com.swjjang7.reportmillie.repository.remote.NetworkImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticleImpl @Inject constructor(
    private val networkImpl: NetworkImpl,
    private val articleDao: ArticleDao,
) {
    suspend fun getNewsList() {
        val readList = articleDao.findAllReadList().first().map { it.title }
        val networkList = try {
            networkImpl.getNewsList().map {
                if (readList.contains(it.title)) {
                    it.copy(read = true)
                } else {
                    it
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            listOf<Article>()
        }

        withContext(Dispatchers.IO) {
            articleDao.upsertArticleList(networkList)
        }
    }

    fun findAll(): Flow<List<Article>> {
        return articleDao.findAll()
    }

    suspend fun updateArticle(article: Article) {
        withContext(Dispatchers.IO) {
            articleDao.updateArticle(article.copy(read = true))
        }
    }
}