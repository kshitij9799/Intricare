package com.example.intricare

import com.example.intricare.data.LoginRequest
import com.example.intricare.data.LoginUser
import com.example.intricare.data.Message
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("api/postoffice/Vadodara")
    fun getPostOffices(): Call<Message>

    @POST("auth/login")
    fun getLoginConfirmation(@Body credentials: LoginRequest): Call<LoginUser>

}