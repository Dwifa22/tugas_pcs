package com.example.pizzaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class TransaksiAdapter(): RecyclerView.Adapter<TransaksiAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val txtId:TextView
        val txtNama:TextView
        val txtHarga:TextView
        val imgMenu: ImageView

        init {
            txtId =itemView.findViewById(R.id.textViewIdMakanan)
            txtNama = itemView.findViewById(R.id.textNamaMakanan)
            txtHarga = itemView.findViewById(R.id.textHargaMakanan)
            imgMenu = itemView.findViewById(R.id.imageMakanan)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.cardview_transaksi,parent,false)

        return ViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtId.text = listId[position].toString()
        holder.txtNama.text = listNama[position]
        holder.txtHarga.text = listHarga[position].toString()
        val gambar:String = listFoto[position]

        Picasso.get().load(gambar).into(holder.imgMenu)
    }

    override fun getItemCount():Int = listId.size

    companion object{
        var jumlah:Int = 0
        var listId = listOf<String>()
        var listNama = listOf<String>()
        var listHarga = listOf<Int>()
        var listFoto = listOf<String>()

        var harga:Int = 0;
    }
}