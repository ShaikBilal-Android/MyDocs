package com.example.example

import com.google.gson.annotations.SerializedName


data class Mobiledata (

  @SerializedName("products" ) var products : ArrayList<Products> = arrayListOf(),
  @SerializedName("total"    ) var total    : Int?                = null,
  @SerializedName("skip"     ) var skip     : Int?                = null,
  @SerializedName("limit"    ) var limit    : Int?                = null

)