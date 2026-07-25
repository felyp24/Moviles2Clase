package com.example.idatdemo.api

import com.example.idatdemo.entity.Producto
import retrofit2.Call
import retrofit2.http.GET

interface FakeStoreApiService {
    @GET("products")
    fun getproducts() : Call<List<Producto>>

}