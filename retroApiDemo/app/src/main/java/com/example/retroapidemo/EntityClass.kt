package com.example.retroapidemo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class District(
    @PrimaryKey(autoGenerate = true)
    val districtId: Int = 0,
    val name: String
)

@Entity
data class Mandal(
    @PrimaryKey(autoGenerate = true)
    val mandalId: Int = 0,
    val name: String,
    val districtId: Int
)

@Entity
data class Village(
    @PrimaryKey(autoGenerate = true)
    val villageId: Int = 0,
    val name: String,
    val mandalId: Int
)