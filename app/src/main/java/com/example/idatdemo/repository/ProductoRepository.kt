package com.example.idatdemo.repository

import android.content.ContentValues
import android.content.Context
import com.example.idatdemo.Data.AppDatabaseHelper
import com.example.idatdemo.entity.Producto
//equisde
class ProductoRepository(context: Context) {

    private val dbhelper = AppDatabaseHelper(context)

    fun insertar(producto : Producto): Long{
        val db = dbhelper.writableDatabase
        val valores = ContentValues().apply {
            put("id", producto.id)
            put("title",producto.title)
            put("price",producto.price)
            put("description",producto.description)
            put("image",producto.image)
        }
        return db.insert("producto",null,valores)
    }
}
