package com.example.googlebooks
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView.Adapter
//import androidx.recyclerview.widget.RecyclerView.ViewHolder
//import com.bumptech.glide.Glide
//import com.example.example.Items
//
//class RecyclerAdapter(val context:Context,val item:List<Items> ):Adapter<RecyclerAdapter.RecyclerViewHolder>() {
//    class RecyclerViewHolder(view: View):ViewHolder(view) {
//        val BookImg:ImageView = view.findViewById(R.id.book_img)
//        val BookTittle:TextView = view.findViewById(R.id.book_name)
//        val BookAut:TextView = view.findViewById(R.id.book_aut)
//
//    }
//    // It is responsible for inflating the template
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
//        val v = LayoutInflater.from(context).inflate(R.layout.single_view_list,parent,false)
//        return RecyclerViewHolder(v)
//    }
//
//    override fun getItemCount(): Int {
//        return item.size
//    }
//    // iT IS RESPO FOR HOW TO POPULATE DATA
//    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
//        // Loading Img url using Glide image loader
//        Glide.with(context).load(item.get(position).volumeInfo?.imageLinks?.thumbnail).into(holder.BookImg)
//        holder.BookTittle.text = item.get(position).volumeInfo?.title
//        holder.BookAut.text =""
//        var i:Int = 0
//        val item = item
//        val auther = item.get(position).volumeInfo?.authors
//        // iterating the authors because there may be more then authors
//        while (i<auther?.size!!){
////            holder.BookAut.text = holder.auther.text.toString()+" "+auther?.get(i)
//            holder.BookAut.text = holder.BookAut.text.toString()+" "+auther?.get(i)
//            i++
//        }
//    }
//}

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.example.Items

class RecycleAdapter(val context: Context, val items:List<Items>): Adapter<RecycleAdapter.RecycleViewHolder>() {

    class RecycleViewHolder(val view: View):ViewHolder(view){
        val iv:ImageView = view.findViewById(R.id.bookcover)
        val title:TextView = view.findViewById(R.id.booktitle)
        val author:TextView = view.findViewById(R.id.bookauthor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.single_view_list,parent,false)
        return RecycleViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        // how to show an image to the user from the url
        Glide.with(context).load(items.get(position).volumeInfo?.imageLinks?.thumbnail).into(holder.iv)
        holder.title.text = items.get(position).volumeInfo?.title
        holder.author.text = ""
        var i:Int=0
        val items = items
        val authors = items.get(position).volumeInfo?.authors
        while(i< authors?.size!!){
            holder.author.text = holder.author.text.toString()+" "+authors?.get(i)
            i++
        }

        holder.view.setOnClickListener { v->
            val i = Intent(context,BookDetailsActivity::class.java)
            i.putExtra("ITEM",items.get(position))
            context.startActivity(i)
        }
    }
}