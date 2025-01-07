package com.example.roomuser

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM user_table WHERE id = :id")
    suspend fun getUserById(id: Int): User?
}

