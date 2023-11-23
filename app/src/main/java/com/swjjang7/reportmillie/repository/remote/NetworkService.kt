package com.swjjang7.reportmillie.repository.remote

import com.swjjang7.reportmillie.repository.dto.BaseListDto
import com.swjjang7.reportmillie.repository.remote.model.ArticleData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface NetworkService {
    @Headers(
        "Accept: application/json;charset=UTF-8",
        "Content-type: application/json;charset=UTF-8"
    )
    @GET("v2/top-headlines?country=kr&apiKey=3ade64cc29c44b10a89b75f649621cca")
    suspend fun getNewsList(): Response<BaseListDto<ArticleData>>
}