package com.example.wishlist
import android.location.GnssAntennaInfo.Listener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer.ListListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var items: MutableList<Item> = mutableListOf()
        val button = findViewById<Button>(R.id.btnSubmit)
        val itemName = findViewById<View>(R.id.item_name) as EditText
        val itemPrice = findViewById<View>(R.id.price) as EditText
        val itemURL = findViewById<View>(R.id.url) as EditText
        val Rv = findViewById<RecyclerView>(R.id.Rv)

        var adapter = itemAdapter(items)
        Rv.adapter = adapter
        Rv.layoutManager = LinearLayoutManager(this)


        //Add Item
        button.setOnClickListener{

            val name = itemName.text.toString()
            val price = itemPrice.text.toString()
            val url = itemURL.text.toString()
            val newItem = Item(name, price, url)

            items.add(newItem)
            adapter.notifyDataSetChanged()

            itemName.text.clear()
            itemPrice.text.clear()
            itemURL.text.clear()
        }

        //Remove Item
        adapter.setOnItemClickListener(object: itemAdapter.onItemClickListener{

            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity, "Item removed at position $position", Toast.LENGTH_LONG).show()
                items.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        })

    }
}