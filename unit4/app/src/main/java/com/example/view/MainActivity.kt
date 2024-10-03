package com.example.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var buttonClick: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter
    private var items = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        buttonClick = findViewById(R.id.buttonClick)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        itemAdapter = ItemAdapter(items)
        recyclerView.adapter = itemAdapter

        buttonClick.setOnClickListener {
            showAlertDialog()
        }

        imageView.setOnClickListener {
            // Handle image click event here
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            addItem()
        }
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert Dialog")
        builder.setMessage("This is an alert dialog!")
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }

    private fun addItem() {
        val newItem = "Item ${items.size + 1}"
        items.add(newItem)
        itemAdapter.notifyItemInserted(items.size - 1)
        recyclerView.scrollToPosition(items.size - 1)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // Handle settings action
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
