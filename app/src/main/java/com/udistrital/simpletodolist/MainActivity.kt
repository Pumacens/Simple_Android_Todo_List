package com.udistrital.simpletodolist

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var btnAdd: Button
    lateinit var etItemToAdd: EditText
    lateinit var lvItems: ListView

    // Containers for SharedPreferences
    var itemsListArray: ArrayList<String> = arrayListOf<String>()

    // Create sharedPreferences object
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvItems = findViewById(R.id.lvItems)
        btnAdd = findViewById(R.id.btnAdd)
        etItemToAdd = findViewById(R.id.etItem)


        btnAdd.setOnClickListener {
            var itemText: String = etItemToAdd.text.toString().trim()
            if (!itemText.isEmpty()){
                itemsListArray.add(itemText)
                var arrayadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsListArray)
                lvItems.adapter = arrayadapter
                etItemToAdd.text = null

            } else{
                Toast.makeText(applicationContext, "You have to write the item name", Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onPause() {
        super.onPause()
    }

    fun saveListItemsData(){
        sharedPreferences = this.getSharedPreferences("", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()
    }
}