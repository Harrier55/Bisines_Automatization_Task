package com.example.bisinesautomatizationtask.data.datasourse

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

const val BASEURL = "http://195.69.218.233:54000/Test/"

object RetrofitApi {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
     }

    fun getApi():Api{
        return getRetrofit().create(Api::class.java)
    }
}