package com.example.filmia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class MyMovieAdapter(val context: Context,val l:MutableList<MyMovies>): Adapter<MyMovieAdapter.MFMViewHolder>() {
    class MFMViewHolder(v:View): ViewHolder(v) {
        // Providing a information to our CustomViewHolder
        val iv:ImageView = v.findViewById(R.id.imageView)
        val tv:TextView = v.findViewById(R.id.textView)
    }
    // This method is responsible for item which is about to be display
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MFMViewHolder {
        val getinfo:View = LayoutInflater.from(context).inflate(R.layout.single_item_list,parent,false)
        return MFMViewHolder(getinfo)
    }
    // Total number of item to display
    override fun getItemCount(): Int {
        return  l.size
    }
    // respo for populaitng a data of every item of recyclerview
    override fun onBindViewHolder(holder: MFMViewHolder, position: Int) {
        holder.iv.setImageResource(l.get(position).mposter)
        holder.tv.text = l.get(position).mname
    }
}