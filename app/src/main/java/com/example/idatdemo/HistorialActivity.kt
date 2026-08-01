package com.example.idatdemo

import android.content.Intent
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
import com.example.idatdemo.entity.Rating
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistorialActivity : AppCompatActivity() {

    private lateinit var rvHistorial: RecyclerView
    private lateinit var historialAdapter: HistorialAdapter
    private lateinit var txtBuscarHistorial: TextInputEditText
    private lateinit var btnBuscarHistorial: MaterialButton

    private val productos = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_historial)

        rvHistorial = findViewById(R.id.rvhistorial)
        txtBuscarHistorial = findViewById(R.id.txtbuscarhistorial)
        btnBuscarHistorial = findViewById(R.id.btnbuscarhistorial)

        rvHistorial.layoutManager = LinearLayoutManager(this)

        historialAdapter = HistorialAdapter(
            context = this,
            lista = productos
        ) { productoSeleccionado ->

            abrirDetalleProducto(productoSeleccionado)
        }

        rvHistorial.adapter = historialAdapter

        //cargarProductosDesdeApi()
        cargarproductosdesdefirebase()

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { view, insets ->

            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }
    }

    private fun abrirDetalleProducto(producto: Producto) {

        val intent = Intent(
            this,
            ProductoSeleccionadoActivity::class.java
        ).apply {

            putExtra("producto_id", producto.id)
            putExtra("producto_titulo", producto.title)
            putExtra("producto_precio", producto.price)
            putExtra("producto_descripcion", producto.description)
            putExtra("producto_categoria", producto.category)
            putExtra("producto_imagen", producto.image)
            putExtra("producto_calificacion", producto.rating.rate)
            putExtra("producto_cantidad", producto.rating.count)
        }

        startActivity(intent)
    }

    private fun cargarProductosDesdeApi() {

        FakeStoreApiClient.apiService
            .getproducts()
            .enqueue(object : Callback<List<Producto>> {

                override fun onResponse(
                    call: Call<List<Producto>>,
                    response: Response<List<Producto>>
                ) {
                    if (response.isSuccessful) {

                        val listaRecibida = response.body()

                        if (listaRecibida != null) {
                            productos.clear()
                            productos.addAll(listaRecibida)

                            historialAdapter.notifyDataSetChanged()
                        }
                    } else {
                        Toast.makeText(
                            this@HistorialActivity,
                            "No se pudieron cargar los productos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(
                    call: Call<List<Producto>>,
                    throwable: Throwable
                ) {
                    Log.e(
                        "HistorialActivity",
                        "Error al cargar productos",
                        throwable
                    )

                    Toast.makeText(
                        this@HistorialActivity,
                        throwable.message ?: "Error de conexión",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    private fun cargarproductosdesdefirebase(){
        val referencia = FirebaseDatabase.getInstance().getReference("productos")
        Log.i("Firebase","Referencia" + referencia)
        referencia.addListenerForSingleValueEvent(
            object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    productos.clear()
                    for (item in snapshot.children){
                        //val id = item.key
                        val title = item.child("title")
                        val description = item.child("description")
                        val price = item.child("price")
                        val category = item.child("category")
                        val image = item.child("image")
                        productos.add(Producto(
                            id = 0,
                            title = title.value.toString(),
                            description = description.value.toString(),
                            price = price.value.toString().toDouble(),
                            category = category.value.toString(),
                            image = image.value.toString(),
                            rating = Rating(
                                rate = 0.0,
                                count = 0
                            )

                        ))
                    }
                    historialAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )
    }
}