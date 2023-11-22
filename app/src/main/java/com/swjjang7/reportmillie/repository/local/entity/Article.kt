package com.swjjang7.reportmillie.repository.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @Embedded(prefix = "source")
    val source: Source?,

    val author: String?,

    val title: String?,

    val description: String?,

    val url: String?,

    val urlToImage: String?,

    val publishedAt: String?,

    val content: String?,

    var readTime: Long? = null,
)
