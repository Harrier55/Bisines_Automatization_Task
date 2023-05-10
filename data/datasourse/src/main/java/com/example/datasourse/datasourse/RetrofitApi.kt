package com.example.datasourse.datasourse



import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitApi {

    private const val BASEURL = "http://195.69.218.233:54000/Test/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
     }

    fun getApi(): Api {
        return getRetrofit().create(Api::class.java)
    }
}