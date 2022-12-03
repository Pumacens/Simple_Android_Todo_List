package com.udistrital.simpletodolist

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var btnAdd: Button
    lateinit var etItemToAdd: EditText
    lateinit var lvItems: ListView

    // Containers for SharedPreferences
    var itemsListArray: ArrayList<String> = arrayListOf<String>()

    // Create sharedPreferences object
    lateinit var sharedPreferences: SharedPreferences
    var fileHelper: FileHelper = FileHelper()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvItems = findViewById(R.id.lvItems)
        btnAdd = findViewById(R.id.btnAdd)
        etItemToAdd = findViewById(R.id.etItem)

        itemsListArray = fileHelper.readData(this)

        var arrayadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsListArray)
        lvItems.adapter = arrayadapter

        btnAdd.setOnClickListener {
            var itemText: String = etItemToAdd.text.toString().trim()
            if (!itemText.isEmpty()){
                itemsListArray.add(itemText)
                etItemToAdd.setText("")
                fileHelper.writeData(itemsListArray, applicationContext)
                arrayadapter.notifyDataSetChanged()

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