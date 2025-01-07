package com.example.example

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class IndustryIdentifiers (

  @SerializedName("type"       ) var type       : String? = null,
  @SerializedName("identifier" ) var identifier : String? = null

): Serializable