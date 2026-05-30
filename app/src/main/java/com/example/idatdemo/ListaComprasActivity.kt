package com.example.idatdemo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ListaComprasActivity : AppCompatActivity() {

    private lateinit var lvproductos : ListView
    private lateinit var txtbuscar : TextInputEditText
    private lateinit var btninsert : MaterialButton
    private lateinit var tilproducto : TextInputLayout
    private val productos = mutableListOf<String>()
    private lateinit var productosadapter : ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_compras)

        lvproductos = findViewById(R.id.lvproductos)
        txtbuscar = findViewById(R.id.txtbuscar)
        tilproducto = findViewById(R.id.tilproducto)
        btninsert = findViewById(R.id.btninsert)
        productos.add("Arroz")
        productos.add("Azucar")
        productosadapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            productos
        )

        lvproductos.adapter = productosadapter
        btninsert.setOnClickListener{
            val producto = txtbuscar.text.toString().trim()
            if(producto.isNotEmpty()){
                productos.add(producto)
                productosadapter.notifyDataSetChanged()
                txtbuscar.text?.clear()
            }
            else{
                Toast.makeText(this,"Ingrese el valor",Toast.LENGTH_SHORT).show()
            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}