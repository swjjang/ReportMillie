package com.swjjang7.reportmillie.repository.remote.entity

/*
 아티클의 출처를 알려주는 데이터로 보임
 id 는 어떤것으로 들어올지 모름
 name 은 출처명인걸로 보임
 */
data class Source(
    val id: String? = null,

    val name: String? = null,
)
