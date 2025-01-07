package com.example.retroapidemo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [District::class, Mandal::class, Village::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun districtDao(): DistrictDao
    abstract fun mandalDao(): MandalDao
    abstract fun villageDao(): VillageDao
}
