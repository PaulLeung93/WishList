package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class itemAdapter(private val itemList: List<Item>)  : RecyclerView.Adapter<itemAdapter.ViewHolder>(){

    //Listener member variable
    private lateinit var mListener : onItemClickListener

    //Define Listener Interace
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    //Define method to allow parent activity/fragment to define the listener
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    class ViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val itemTextView: TextView
        val priceTextView: TextView
        val urlTextView: TextView

        init {
            itemTextView = itemView.findViewById(R.id.itemName)
            priceTextView = itemView.findViewById(R.id.itemPrice)
            urlTextView = itemView.findViewById(R.id.Store_url)

            //Long Click Listener, requires boolean to be returned
            itemView.setOnLongClickListener {
                listener.onItemClick(adapterPosition)
                true
            }

            //Regular Click Listener
//          itemView.setOnClickListener {
//              listener.onItemClick(adapterPosition)
//          }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList.get(position)
        holder.itemTextView.text = item.Name
        holder.priceTextView.text = item.Price
        holder.urlTextView.text= item.URL

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}