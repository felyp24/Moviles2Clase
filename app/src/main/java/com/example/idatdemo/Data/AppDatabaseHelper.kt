package com.example.idatdemo.Data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabaseHelper(context : Context) : SQLiteOpenHelper(context, "productos.db",null,4){
    override fun onCreate(p0: SQLiteDatabase) {
        p0.execSQL("""
            create table producto(
                id INTEGER Primary Key Autoincrement not null,
                title Text,
                price REAL,
                description TEXT,
                category TEXT,
                image TEXT
                );
""".trimIndent())
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.execSQL("Drop table if exists producto")
        onCreate(p0)
    }


}