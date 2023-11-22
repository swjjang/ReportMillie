package com.swjjang7.reportmillie.repository.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(
    @Embedded(prefix = "source")
    val source: Source?,

    val author: String?,

    @PrimaryKey
    val title: String,

    val description: String?,

    val url: String?,

    val urlToImage: String?,

    val publishedAt: String?,

    val content: String?,

    val read: Boolean = false,
)
