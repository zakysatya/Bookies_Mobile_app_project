package com.example.se_aol.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, "dbBookies.db", null, 2) {

    override fun onCreate(db: SQLiteDatabase?) {
        val queryUsers = """
            CREATE TABLE IF NOT EXISTS Users (
                Email VARCHAR(36) PRIMARY KEY,
                Username VARCHAR(100),
                Password VARCHAR(64)
            )
        """
        db?.execSQL(queryUsers)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Users")
        onCreate(db)
    }
}