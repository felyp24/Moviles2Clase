package com.example.idatdemo.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.idatdemo.Data.AppDatabaseHelper
import com.example.idatdemo.entity.Producto
//equisde2
class ProductoRepository(context: Context) {

    private val dbhelper = AppDatabaseHelper(context)

    fun insertar(producto : Producto): Long{
        val db = dbhelper.writableDatabase
        val valores = ContentValues().apply {
            put("title",producto.title)
            put("price",producto.price)
            put("description",producto.description)
            put("image",producto.image)
            put("category",producto.category)
        }
        val id = db.insert("producto",null,valores)
        db.close()
        return id
    }

    fun listarproductos() : List<Producto>{
        val db = dbhelper.readableDatabase
        val lista = mutableListOf<Producto>()
        val cursor : Cursor = db.rawQuery("Select * from Producto",null)
        while (cursor.moveToNext()){
            lista.add(
                Producto(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    title = cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    price = cursor.getDouble(cursor.getColumnIndexOrThrow("price")),
                    description = cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    category = cursor.getString(cursor.getColumnIndexOrThrow("category")),
                    image = cursor.getString(cursor.getColumnIndexOrThrow("image"))
                )
            )
        }
        cursor.close()
        db.close()
        return lista

    }
    fun buscarhistorialproducto(titulo : String) : List<Producto>{
        val db = dbhelper.readableDatabase
        val lista = mutableListOf<Producto>()
        val cursor : Cursor = db.rawQuery("Select * from Producto where title = '${titulo}'",null)
        while(cursor.moveToNext()){
            lista.add(
                Producto(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    title = cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    price = cursor.getDouble(cursor.getColumnIndexOrThrow("price")),
                    description = cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    category = cursor.getString(cursor.getColumnIndexOrThrow("category")),
                    image = cursor.getString(cursor.getColumnIndexOrThrow("image"))
                )
            )
        }
        cursor.close()
        db.close()
        return lista
    }
}
