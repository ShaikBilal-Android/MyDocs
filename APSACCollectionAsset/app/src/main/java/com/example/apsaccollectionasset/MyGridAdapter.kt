package com.example.apsaccollectionasset

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MyGridAdapter(private val context: Context, private val data: List<MyDataItem>) : BaseAdapter() {

    // Model class to hold your grid item data (replace with your actual data structure)
    data class MyDataItem(val imageResourceId: Int, val text: String)

    override fun getCount(): Int = data.size

    override fun getItem(position: Int): Any = data[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val view: View

        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.card_item, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val item = data[position]
        viewHolder.imageView.setImageResource(item.imageResourceId)
        viewHolder.textView.text = item.text

        view.setOnClickListener {
            val item =data[position]
            val toastmsg = "Item Clicked: ${item.text}"
            Toast.makeText(context,toastmsg,Toast.LENGTH_LONG).show()
        }

        return view
    }

    class ViewHolder(view: View) {
        val imageView: ImageView = view.findViewById(R.id.item_image)
        val textView: TextView = view.findViewById(R.id.item_text)
    }
}
