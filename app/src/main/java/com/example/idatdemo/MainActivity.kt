package com.example.idatdemo

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var tvRegistro : TextView
    private lateinit var btnentrar : MaterialButton
    private lateinit var tvtitulo : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        tvRegistro = findViewById(R.id.tvRegistro)
        tvRegistro.setOnClickListener {
            var intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
        btnentrar = findViewById(R.id.btnentrar)
        btnentrar.setOnClickListener{
            val intent = Intent(this, InicioActivity::class.java)
            startActivity(intent)
        }
        //Otra forma de cambiar el tipo de letra
//        tvtitulo = findViewById(R.id.tvtitulo)
//        val cambiarletra = ResourcesCompat.getFont(this,R.font.quicksandregular)
//        tvtitulo.typeface= cambiarletra


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
