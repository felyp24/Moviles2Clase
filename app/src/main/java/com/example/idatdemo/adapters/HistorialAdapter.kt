package com.example.idatdemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.idatdemo.R
import com.example.idatdemo.entity.Producto

class HistorialAdapter(private val context : Context ,private val lista : List<Producto>) : RecyclerView.Adapter<HistorialAdapter.HistorialViewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_historial,parent,false)
        return HistorialViewholder(view)
    }

    override fun onBindViewHolder(holder: HistorialViewholder, position: Int) {
        val producto = lista[position]
        Glide.with(context).load(producto.image).into(holder.ivimage)
        holder.tvtitle.text = producto.title
        holder.tvdescription.text = producto.description
        holder.tvcategory.text = producto.category
        holder.tvprice.text = "S/ ${String.format("%.2f",producto.price)}"
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class HistorialViewholder(itemview : View) : RecyclerView.ViewHolder(itemview){
        val ivimage : ImageView = itemview.findViewById<ImageView>(R.id.ivimage)
        val tvtitle : TextView = itemview.findViewById<TextView>(R.id.tvtitle)
        val tvdescription : TextView = itemview.findViewById<TextView>(R.id.tvdescription)
        val tvcategory : TextView = itemview.findViewById<TextView>(R.id.tvcategory)
        val tvprice : TextView = itemview.findViewById<TextView>(R.id.tvprice)
    }
}