package com.example.retroapidemo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DistrictDao {
    @Insert
    fun insertDistrict(district: District):Long

    @Query("SELECT * FROM District")
    fun getAllDistricts(): List<District>
}

@Dao
interface MandalDao {
    @Insert
    fun insertMandal(mandal: Mandal):Long

    @Query("SELECT * FROM Mandal WHERE districtId = :districtId")
    fun getMandalsForDistrict(districtId: Int): List<Mandal>
}

@Dao
interface VillageDao {
    @Insert
    fun insertVillage(village: Village):Long

    @Query("SELECT * FROM Village WHERE mandalId = :mandalId")
    fun getVillagesForMandal(mandalId: Int): List<Village>
}