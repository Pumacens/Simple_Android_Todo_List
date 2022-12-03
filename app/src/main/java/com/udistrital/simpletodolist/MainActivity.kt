package com.udistrital.simpletodolist

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
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
        
        lvItems.setOnItemClickListener { adapterView, view, i, l ->
            showAlertDialog(i, arrayadapter)
        }


    }

    fun showAlertDialog(itemPos: Int, arrayAdapter: ArrayAdapter<String>){
        var alertDialog = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Delete")
            .setMessage("Do you what to remove the item from the list?")
            .setIcon(R.drawable.warning)
            .setCancelable(false)
            .setNegativeButton("No", DialogInterface.OnClickListener {dialogInterface, whichSelected ->
                dialogInterface.cancel()
            })
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                itemsListArray.removeAt(itemPos)
                arrayAdapter.notifyDataSetChanged()
            })

        alertDialog.create().show()
    }


}