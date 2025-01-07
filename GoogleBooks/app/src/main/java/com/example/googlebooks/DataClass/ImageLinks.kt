package com.example.example

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ImageLinks (

  @SerializedName("smallThumbnail" ) var smallThumbnail : String? = null,
  @SerializedName("thumbnail"      ) var thumbnail      : String? = null

): Serializable