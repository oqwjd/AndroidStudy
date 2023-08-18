package com.example.studyproject.dataSaveStudy

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

/***
 * TODO SQLiteOpenHelper第三个参数CursorFactory可以传null表示默认值，还有很多其他构造体
 */
class MyDataBaseHelper(val context: Context,name:String,version : Int):SQLiteOpenHelper(context,name,null,version) {

    private val createBook = "create table book (" +
            "id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text," +
            "category_id integer)"

    private val createCategory = "create table Category(" +
            "id integer primary key autoincrement," +
            "category_name text," +
            "category_id integer)"

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(createBook)
            db.execSQL(createCategory)
            Toast.makeText(context,"create table book success",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"create table book failed",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null){
            if(oldVersion <= 1) db.execSQL(createCategory)
            if (oldVersion <= 2) db.execSQL("alter table book add column category_id integer")
        }else{
            Toast.makeText(context,"bookStore is null",Toast.LENGTH_SHORT).show()
        }

    }
}