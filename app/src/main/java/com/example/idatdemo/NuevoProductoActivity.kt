package com.example.idatdemo

import android.os.Bundle
import android.util.Log
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
import com.google.firebase.Firebase
import com.google.firebase.database.database
import java.util.UUID

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
            val db = Firebase.database.reference
            val productomap = mapOf(
                "title" to title,
                "price" to price,
                "description" to description,
                "category" to category,
                "image" to imagen
            )
            val uuid = UUID.randomUUID().toString()
            db.child("productos").child(uuid).setValue(productomap)
                .addOnSuccessListener {
                    Log.e("Firebase","Producto Insertado")
                    Toast.makeText(this,"Producto insertado",Toast.LENGTH_SHORT).show()
                    finish()

                }
                .addOnFailureListener{error->
                    Log.e("Firebase",error.toString())
                    Toast.makeText(this,"Producto no insertado",Toast.LENGTH_SHORT).show()
                }
//            val productoRepository = ProductoRepository(this)
//            val idproducto =productoRepository.insertar(
//                Producto(
//                    id=0,
//                    title = title,
//                    price = price,
//                    category = category,
//                    description = description,
//                    image = imagen,
//                    rating = Rating(
//                        rate=0.0,
//                        count = 0
//                    )
//                )
//            )

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}