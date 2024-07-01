package com.example.intricare.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("Name")
    @Expose
    val name: String,
    @SerializedName("Description")
    @Expose
    val description: String,
    @SerializedName("PINCode")
    @Expose
    val pinCode: String,
    @SerializedName("BranchType")
    @Expose
    val branchType: String,
    @SerializedName("DeliveryStatus")
    @Expose
    val deliveryStatus: String,
    @SerializedName("Taluk")
    @Expose
    val taluk: String,
    @SerializedName("Circle")
    @Expose
    val circle: String,
    @SerializedName("District")
    @Expose
    val district: String,
    @SerializedName("Division")
    @Expose
    val division: String,
    @SerializedName("Region")
    @Expose
    val region: String,
    @SerializedName("State")
    @Expose
    val state: String,
    @SerializedName("Country")
    @Expose
    val country: String,
)
