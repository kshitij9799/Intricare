package com.example.intricare.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("PostOffice")
    @Expose
    val postOffice: List<Post>
)
