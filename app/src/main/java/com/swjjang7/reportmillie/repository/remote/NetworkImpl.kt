package com.swjjang7.reportmillie.repository.remote

import com.swjjang7.reportmillie.repository.remote.entity.Article
import com.swjjang7.reportmillie.repository.remote.entity.Source
import javax.inject.Inject

class NetworkImpl @Inject constructor(
    private val service: NetworkService
) {
    suspend fun getNewsList(): List<Article> {
        val list = service.getNewsList().body()?.articles
        return list?.map {
            Article(
                it.source?.let { source ->
                    Source(
                        source.id,
                        source.name
                    )
                },
                it.author,
                it.title,
                it.description,
                it.url,
                it.urlToImage,
                it.publishedAt,
                it.content,
            )
        } ?: listOf<Article>()
    }
}