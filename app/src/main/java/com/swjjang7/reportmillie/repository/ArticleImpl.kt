package com.swjjang7.reportmillie.repository

import com.swjjang7.reportmillie.repository.local.ArticleDao
import com.swjjang7.reportmillie.repository.local.entity.Article
import com.swjjang7.reportmillie.repository.remote.NetworkImpl
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ArticleImpl @Inject constructor(
    private val networkImpl: NetworkImpl,
    private val articleDao: ArticleDao,
) {
    suspend fun getNewsList(): List<Article> {
        val networkList = try {
            networkImpl.getNewsList()
        } catch (e: Exception) {
            e.printStackTrace()
            listOf<Article>()
        }

        val list = if (networkList.isNotEmpty()) {
            articleDao.upsertArticleList(networkList)
            networkList
        } else {
            articleDao.findAll().first()
        }

        return list
    }
}