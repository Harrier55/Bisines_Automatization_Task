package com.example.bisinesautomatizationtask.data.datasourse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers

interface Api {
    /** одежда **/
    @GET("hs/exch/goods/")
    suspend fun getDress(@HeaderMap headers:Map<String, String>): Response<List<DressSourceEntity>>

    /** магазины */
    @GET("hs/exch/stores/")
    suspend fun getStores(@HeaderMap headers:Map<String, String>): Response<List<StoriesSourceEntity>>
}