package com.swjjang7.reportmillie.repository.dto

import com.google.gson.annotations.SerializedName

data class BaseListDto<T>(
    @SerializedName(value = "status")
    val status: String,

    @SerializedName(value = "totalResults")
    val totalResults: Int = 0,

    @SerializedName(value = "articles")
    val articles: List<T>? = null,
)