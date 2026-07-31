package com.example.idatdemo

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import java.util.Locale

class ProductoSeleccionadoActivity : AppCompatActivity() {

    private lateinit var ivDetalleProducto: ImageView
    private lateinit var tvDetalleTitulo: TextView
    private lateinit var tvDetalleCategoria: TextView
    private lateinit var tvDetallePrecio: TextView
    private lateinit var tvDetalleDescripcion: TextView
    private lateinit var rbDetalleCalificacion: RatingBar
    private lateinit var tvDetalleCantidad: TextView
    private lateinit var btnVolver: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_producto_seleccionado)

        inicializarVistas()
        mostrarInformacionProducto()

        btnVolver.setOnClickListener {
            finish()
        }

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

    private fun inicializarVistas() {

        ivDetalleProducto = findViewById(R.id.ivDetalleProducto)
        tvDetalleTitulo = findViewById(R.id.tvDetalleTitulo)
        tvDetalleCategoria = findViewById(R.id.tvDetalleCategoria)
        tvDetallePrecio = findViewById(R.id.tvDetallePrecio)
        tvDetalleDescripcion = findViewById(R.id.tvDetalleDescripcion)
        rbDetalleCalificacion = findViewById(R.id.rbDetalleCalificacion)
        tvDetalleCantidad = findViewById(R.id.tvDetalleCantidad)
        btnVolver = findViewById(R.id.btnVolver)
    }

    private fun mostrarInformacionProducto() {

        val titulo = intent.getStringExtra("producto_titulo")
            ?: intent.getStringExtra("nombreproducto")
            ?: "Producto sin nombre"

        val categoria = intent.getStringExtra("producto_categoria")
            ?: "Sin categoría"

        val descripcion = intent.getStringExtra("producto_descripcion")
            ?: "Este producto no tiene una descripción disponible."

        val imagen = intent.getStringExtra("producto_imagen")

        val precio = intent.getDoubleExtra(
            "producto_precio",
            0.0
        )

        val calificacion = intent.getDoubleExtra(
            "producto_calificacion",
            0.0
        )

        val cantidadValoraciones = intent.getIntExtra(
            "producto_cantidad",
            0
        )

        tvDetalleTitulo.text = titulo
        tvDetalleCategoria.text = "Categoría: $categoria"

        tvDetallePrecio.text = String.format(
            Locale.getDefault(),
            "S/ %.2f",
            precio
        )

        tvDetalleDescripcion.text = descripcion
        rbDetalleCalificacion.rating = calificacion.toFloat()

        tvDetalleCantidad.text =
            "Cantidad de valoraciones: $cantidadValoraciones"

        if (!imagen.isNullOrBlank()) {
            Glide.with(this)
                .load(imagen)
                .into(ivDetalleProducto)
        } else {
            ivDetalleProducto.setImageResource(
                R.drawable.cart_shopping_fast_svgrepo_com
            )
        }
    }
}