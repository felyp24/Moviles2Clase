package com.example.idatdemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.idatdemo.entity.Producto
import com.example.idatdemo.entity.Rating
import com.example.idatdemo.repository.ProductoRepository

class NuevoProductoActivity : AppCompatActivity() {
    private lateinit var ettitle : EditText
    private lateinit var etprice : EditText
    private lateinit var etdescription : EditText
    private lateinit var etcategory : EditText
    private lateinit var etimage : EditText
    private lateinit var btnguardar : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nuevo_producto)

        ettitle = findViewById(R.id.ettitle)
        etprice = findViewById(R.id.etprice)
        etdescription = findViewById(R.id.etdescription)
        etcategory = findViewById(R.id.etcategory)
        etimage = findViewById(R.id.etimage)
        btnguardar = findViewById(R.id.btnguardar)

        btnguardar.setOnClickListener{
            val title = ettitle.text.toString()
            val price = etprice.text.toString().toDoubleOrNull() ?:0.0
            val description = etdescription.text.toString()
            val category = etcategory.text.toString()
            val imagen = etimage.text.toString()
            val productoRepository = ProductoRepository(this)
            val idproducto =productoRepository.insertar(
                Producto(
                    id=0,
                    title = title,
                    price = price,
                    category = category,
                    description = description,
                    image = imagen,
                    rating = Rating(
                        rate=0.0,
                        count = 0
                    )
                )
            )
            Toast.makeText(this,"Producto insertado con id: $idproducto",Toast.LENGTH_SHORT).show()
            finish()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}