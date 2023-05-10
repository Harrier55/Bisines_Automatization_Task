package com.example.datasourse.datasourse

import com.example.datasourse.datasourse.models_dto.DressSourceDTO
import com.example.datasourse.datasourse.models_dto.StoriesSourceDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap

interface Api {
    /** одежда **/
    @GET("hs/exch/goods/")
    suspend fun getDress(@HeaderMap headers:Map<String, String>): Response<List<DressSourceDTO>>

    /** магазины */
    @GET("hs/exch/stores/")
    suspend fun getStores(@HeaderMap headers:Map<String, String>): Response<List<StoriesSourceDTO>>
}