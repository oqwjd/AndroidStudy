package com.example.studyproject.dataSaveStudy

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivitySqliteSaveBinding

class SQLiteSaveActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var bind : ActivitySqliteSaveBinding
    lateinit var dbHelper : MyDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_save)
        bind = ActivitySqliteSaveBinding.inflate(layoutInflater)
        setContentView(bind.root)
        /***
         * TODO 了解DB是什么时候开始执行的MyDataBaseHelper？dbHelper.writableDatabase
         * 传入version大于之前的，触发onUpgrade
         */
//        dbHelper = MyDataBaseHelper(this,"BookStore.db",1)
        dbHelper = MyDataBaseHelper(this,"BookStore.db",2)
        bind.root.children.forEach { it.setOnClickListener(this) }


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.SQLiteSaveCreate -> createBookStore()
            R.id.SQLiteSaveAdd -> addBook()
            R.id.SQLiteSaveUpgrade ->upgradeBook()
            R.id.SQLiteSaveSearch -> searchBook()
            R.id.SQLiteSaveDelete -> deleteBook()
        }
    }

    private fun createBookStore(){
        dbHelper.writableDatabase
    }

    private fun addBook(){
        val addData = bind.SQLiteSaveAddBook.text.split(":")
        if (addData.size != 4){
            Toast.makeText(this,"wrong data", Toast.LENGTH_SHORT).show()
        }else{
//            val value = ContentValues().apply {
//                put("name",addData[0])
//                put("author",addData[1])
//                put("pages",addData[2])
//                put("price",addData[3])
//            }
            /***
             * 官方简化的方法
             * 1、A to B,生成map<A,B>
             * 2、遍历生成ContentValues对象
             */
            val value = contentValuesOf("name" to addData[0],"author" to addData[1],"pages" to addData[2],"price" to addData[3])
            dbHelper.writableDatabase.insert("Book",null,value)
        }
        bind.SQLiteSaveAddBook.setText("")
    }

    private fun upgradeBook(){
        val editorData = bind.SQLiteSaveChange.text.split(",")
        val db = dbHelper.writableDatabase
        val value = ContentValues().apply{
            for (i in 0 until editorData[0].toInt()){
                val kv = editorData[i+2].split(':')
                put(kv[0],kv[1])
            }
        }
        db.update("Book",value,"id = ?", arrayOf(editorData[1]))
    }


    @SuppressLint("Range", "Recycle")
    private fun searchBook(){
        val editorData = bind.SQLiteSaveSearchData.text.toString()
        val db = dbHelper.writableDatabase
//        val search = if(editorData == "") null else editorData
        val cursor = db.query("Book",null,if(editorData == "") null else editorData,null,null,null,"id DESC")
        val text = StringBuilder()
        if(cursor.moveToFirst()){
            do {
                text.append("id:${cursor.getInt(cursor.getColumnIndex("id"))} " +
                        "name:${cursor.getString(cursor.getColumnIndex("name"))} " +
                        "name:${cursor.getString(cursor.getColumnIndex("author"))} " +
                        "pages:${cursor.getInt(cursor.getColumnIndex("pages"))} " +
                        "price:${cursor.getDouble(cursor.getColumnIndex("price"))}" +
                        "category_id:${cursor.getInt(cursor.getColumnIndex("category_id"))}\n")
            }while (cursor.moveToNext())
        }
        bind.SQLiteSaveShow.text = text.toString()
    }

    private fun deleteBook(){
        val editorData = bind.SQLiteSaveDeleteData.text
        val db = dbHelper.writableDatabase
        db.delete("Book",editorData.toString(),null)
    }

}