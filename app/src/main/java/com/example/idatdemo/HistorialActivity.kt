package com.example.idatdemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idatdemo.adapters.HistorialAdapter
import com.example.idatdemo.entity.Producto

class HistorialActivity : AppCompatActivity() {

    private lateinit var rvhistorial : RecyclerView
    private lateinit var historialadapter : HistorialAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historial)
        rvhistorial = findViewById<RecyclerView>(R.id.rvhistorial)
        rvhistorial.layoutManager = LinearLayoutManager(this)
        val productos = listOf(
            Producto(1,"Producto 1",10.0,"Descripcion producto 1","Categoria A","https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png"),
            Producto(2,"Producto 2",10.0,"Descripcion producto 2","Categoria B","https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png"),
            Producto(3,"Producto 3",10.0,"Descripcion producto 3","Categoria C","https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png")
        )
        historialadapter = HistorialAdapter(this,productos)
        rvhistorial.adapter = historialadapter
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}