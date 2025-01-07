package com.example.example

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class SearchInfo (

  @SerializedName("textSnippet" ) var textSnippet : String? = null

): Serializable