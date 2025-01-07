package com.example.mobiledetails.Mydata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.example.Products
import com.example.mobiledetails.R
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso


class MyAdapter(val context: Context,val prodeuctlist:List<Products>):RecyclerView.Adapter<MyAdapter.MVHolder>(){
    class MVHolder(itemview: View) :RecyclerView.ViewHolder(itemview) {
        var img: ShapeableImageView
        var title : TextView
        var brand : TextView
         init {
             title = itemview.findViewById(R.id.booktitle)
             img = itemview.findViewById(R.id.bookcover)
             brand = itemview.findViewById<TextView?>(R.id.bookauthor)
         }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MVHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.single_item_disp,parent,false)
        return MVHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MVHolder, position: Int) {
        var correntitem = prodeuctlist[position]
        holder.title.text = correntitem.title
        Picasso.get().load(prodeuctlist[position].thumbnail).into(holder.img)
    }

    override fun getItemCount(): Int {
        return prodeuctlist.size

    }
}