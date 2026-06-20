package com.example.idatdemo


import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.idatdemo.ui.InicioFragment
import com.example.idatdemo.ui.PerfilFragment
import com.google.android.material.internal.NavigationMenu
import com.google.android.material.navigation.NavigationView

class InicioActivity : AppCompatActivity() {

    private lateinit var dlaymenu : DrawerLayout
    private lateinit var nvmenu : NavigationView
    private lateinit var ivmenu : ImageView
    private lateinit var flaycontenedor : FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)

        dlaymenu = findViewById<DrawerLayout>(R.id.dlaymenu)
        nvmenu = findViewById<NavigationView>(R.id.nvmenu)
        ivmenu = findViewById<ImageView>(R.id.ivmenu)
        flaycontenedor = findViewById<FrameLayout>(R.id.flaycontenedor)

        ivmenu.setOnClickListener {
            dlaymenu.open()
        }

        nvmenu.setNavigationItemSelectedListener { menuitem ->
            when(menuitem.itemId){
                R.id.inicio -> replaceFragment(InicioFragment())
                R.id.itlista -> startActivity(Intent(this, ListaComprasActivity::class.java))
                R.id.ithistorial -> startActivity(Intent(this, HistorialActivity::class.java))
                R.id.itperfil -> replaceFragment(PerfilFragment())
            }
            dlaymenu.closeDrawers()
            true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dlaymenu)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    private fun replaceFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.flaycontenedor,fragment).commit()
    }
}