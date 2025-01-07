package com.example.sqlitelitedatabase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user_db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_USER = "User"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PHONE = "phone"
        private const val COLUMN_GENDER = "gender"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createUserTable = ("CREATE TABLE " + TABLE_USER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PHONE + " TEXT,"
                + COLUMN_GENDER + " TEXT" + ")")
        db?.execSQL(createUserTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }

    fun addUser(user: User): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_NAME, user.name)
            put(COLUMN_EMAIL, user.email)
            put(COLUMN_PHONE, user.phone)
            put(COLUMN_GENDER, user.gender)
        }
        val success = db.insert(TABLE_USER, null, contentValues)
        db.close()
        return success
    }

    fun getAllUsers(): List<User> {
        val userList: ArrayList<User> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_USER"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val user = User(
                    id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                    phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE)),
                    gender = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER))
                )
                userList.add(user)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return userList
    }

    fun deleteUser(userId: Long): Int {
        val db = this.writableDatabase
        val success = db.delete(TABLE_USER, "$COLUMN_ID=?", arrayOf(userId.toString()))
        db.close()
        return success
    }
}
