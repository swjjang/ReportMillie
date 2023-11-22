package com.swjjang7.reportmillie.repository.remote

import com.swjjang7.reportmillie.repository.local.entity.Article
import com.swjjang7.reportmillie.repository.local.entity.asEntity
import com.swjjang7.reportmillie.repository.remote.model.ArticleData
import javax.inject.Inject

class NetworkImpl @Inject constructor(
    private val service: NetworkService
) {
    suspend fun getNewsList(): List<Article> {
        val list = service.getNewsList().body()?.articles
        return list?.map(ArticleData::asEntity) ?: listOf<Article>()
    }
}