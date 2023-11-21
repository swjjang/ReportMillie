package com.swjjang7.reportmillie.repository.remote.model

import com.google.gson.annotations.SerializedName

data class ArticleData(
    @SerializedName(value = "source")
    val source: SourceData? = null,

    @SerializedName(value = "author")
    val author: String? = null,

    @SerializedName(value = "title")
    val title: String? = null,

    @SerializedName(value = "description")
    val description: String? = null,

    @SerializedName(value = "url")
    val url: String? = null,

    @SerializedName(value = "urlToImage")
    val urlToImage: String? = null,

    @SerializedName(value = "publishedAt")
    val publishedAt: String? = null,

    @SerializedName(value = "content")
    val content: String? = null,
)
