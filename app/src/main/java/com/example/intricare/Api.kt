package com.example.intricare

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    val retrofitService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://www.postalpincode.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    val loginRetrofitService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}