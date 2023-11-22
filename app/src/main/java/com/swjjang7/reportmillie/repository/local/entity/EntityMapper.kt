package com.swjjang7.reportmillie.repository.local.entity

import com.swjjang7.reportmillie.repository.remote.model.ArticleData
import com.swjjang7.reportmillie.repository.remote.model.SourceData

fun ArticleData.asEntity() = Article(
    source = source?.asEntity(),
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content,
)

fun SourceData.asEntity() = Source(
    id = id,
    name = name,
)