package com.example.idatdemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.idatdemo.R
import com.example.idatdemo.entity.Producto

class HistorialAdapter(
    private val context: Context,
    private val lista: List<Producto>,
    private val onItemClick: (Producto) -> Unit
) : RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistorialViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_historial, parent, false)

        return HistorialViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HistorialViewHolder,
        position: Int
    ) {
        val producto = lista[position]

        Glide.with(context)
            .load(producto.image)
            .into(holder.ivImage)

        holder.tvTitle.text = producto.title
        holder.tvCategory.text = producto.category
        holder.tvPrice.text = "S/ ${String.format("%.2f", producto.price)}"
        holder.rbRating.rating = producto.rating.rate.toFloat()

        // Detectamos el clic sobre toda la tarjeta
        holder.itemView.setOnClickListener {
            onItemClick(producto)
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class HistorialViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val ivImage: ImageView =
            itemView.findViewById(R.id.ivimage)

        val tvTitle: TextView =
            itemView.findViewById(R.id.tvtitle)

        val tvCategory: TextView =
            itemView.findViewById(R.id.tvcategory)

        val tvPrice: TextView =
            itemView.findViewById(R.id.tvprice)

        val rbRating: RatingBar =
            itemView.findViewById(R.id.rbrating)
    }
}