package com.udistrital.simpletodolist

import android.content.Context
import java.io.*

class FileHelper {

    val FILENAME: String = "simpleTodoList_itemList.dat"

    fun writeData(item: ArrayList<String>, context: Context){
        var fos: FileOutputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE)
        var oos = ObjectOutputStream(fos)
        oos.writeObject(item)
        oos.close()
    }

    fun readData(context: Context): ArrayList<String> {
        var itemList : ArrayList<String>
        try {
            var fis: FileInputStream = context.openFileInput(FILENAME)
            var ois = ObjectInputStream(fis)
            itemList = ois.readObject() as ArrayList<String>
            return itemList

        } catch (e: FileNotFoundException){
            return ArrayList()
        }
    }
}