package com.example.idatdemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idatdemo.adapters.HistorialAdapter
import com.example.idatdemo.api.FakeStoreApiClient
import com.example.idatdemo.entity.Producto
import com.example.idatdemo.repository.ProductoRepository
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistorialActivity : AppCompatActivity() {

    private lateinit var rvhistorial : RecyclerView
    private lateinit var historialadapter : HistorialAdapter
    private lateinit var txtbuscarhistorial : TextInputEditText
    private lateinit var tilproductohistorial : TextInputLayout
    private lateinit var btnbuscarhistorial : MaterialButton
    private val productos = mutableListOf<Producto>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historial)
        rvhistorial = findViewById<RecyclerView>(R.id.rvhistorial)
        txtbuscarhistorial = findViewById<TextInputEditText>(R.id.txtbuscarhistorial)
        btnbuscarhistorial = findViewById<MaterialButton>(R.id.btnbuscarhistorial)
        rvhistorial.layoutManager = LinearLayoutManager(this)
//        val productos = listOf(
//            Producto(1,"Producto 1",10.0,"Descripcion producto 1","Categoria A","https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png"),
//            Producto(2,"Producto 2",10.0,"Descripcion producto 2","Categoria B","https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png"),
//            Producto(3,"Producto 3",10.0,"Descripcion producto 3","Categoria C","https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png")
//        )
        //val productoRepository = ProductoRepository(this)
        //val productos = productoRepository.listarproductos()
        historialadapter = HistorialAdapter(this,productos)
        rvhistorial.adapter = historialadapter
        cargarproductosdesdeapi()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        btnbuscarhistorial.setOnClickListener{
//            val productos_encontrados = productoRepository.buscarhistorialproducto(txtbuscarhistorial.text.toString())
//            historialadapter = HistorialAdapter(this,productos_encontrados)
//            rvhistorial.adapter = historialadapter
//
//        }
    }

    private fun cargarproductosdesdeapi() {
        FakeStoreApiClient.apiService.getproducts().enqueue(object : Callback<List<Producto>> {
            override fun onResponse(
                call: Call<List<Producto>>,
                response: Response<List<Producto>>
            ) {
                if(response.isSuccessful && response.body() != null){
                    productos.clear()
                    productos.addAll(response.body()!!)
                    historialadapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Producto>>, t: Throwable) {
                Log.e("Error al cargar",t.message.toString())
                Toast.makeText(this@HistorialActivity,t.message.toString(),Toast.LENGTH_SHORT).show()
            }

        })
    }
}