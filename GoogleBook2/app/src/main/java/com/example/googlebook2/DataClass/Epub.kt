package com.example.example

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Epub (

  @SerializedName("isAvailable" ) var isAvailable : Boolean? = null

): Serializable