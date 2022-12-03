package com.udistrital.simpletodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var btnAdd: Button
    lateinit var etItemToAdd: EditText
    lateinit var lvItems: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvItems = findViewById(R.id.lvItems)
        btnAdd = findViewById(R.id.btnAdd)
        etItemToAdd = findViewById(R.id.etItem)

        var itemsList = arrayListOf<String>()


        btnAdd.setOnClickListener {
            var itemText: String = etItemToAdd.text.toString().trim()
            if (!itemText.isEmpty()){
                itemsList.add(itemText)
                var arrayadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsList)
                lvItems.adapter = arrayadapter
            } else{
                Toast.makeText(applicationContext, "You have to write the item name", Toast.LENGTH_LONG)
            }
        }
    }



}