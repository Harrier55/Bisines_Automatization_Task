package com.example.bisinesautomatizationtask.data.datasourse

import com.example.bisinesautomatizationtask.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitApi {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASEURL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
     }

    fun getApi():Api{
        return getRetrofit().create(Api::class.java)
    }
}