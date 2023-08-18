package com.example.studyproject.contentProviderStudy

import android.annotation.SuppressLint
import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.net.Uri
import com.example.studyproject.dataSaveStudy.MyDataBaseHelper

class DatabaseProvider : ContentProvider() {

    private val bookDir = 0
    private val bookItem = 1
    private val categoryDir = 2
    private val categoryItem = 3
    private val authority = "com.example.studyproject.provider"
    private var dbHelper : MyDataBaseHelper? = null

    private val uriMatcher by lazy {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(authority,"book",bookDir)
        matcher.addURI(authority,"book/#",bookItem)
        matcher.addURI(authority,"category",categoryDir)
        matcher.addURI(authority,"category/#",categoryItem)
        matcher
    }

    override fun onCreate()= context?.let {
        dbHelper = MyDataBaseHelper(it,"BookStore",2)
        true
    }?:false


    override fun getType(uri: Uri) = when(uriMatcher.match(uri)) {
        bookDir -> "vnd.android.cursor.dir/$authority.book"
        categoryDir -> "vnd.android.cursor.dir/$authority.category"
        bookItem -> "vnd.android.cursor.item/$authority.book"
        categoryItem -> "vnd.android.cursor.item/$authority.category"
        else->null
    }


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?) = dbHelper?.let {
        val db = it.writableDatabase
        val deleteRows = when(uriMatcher.match(uri)){
            bookDir-> db.delete("book",selection,selectionArgs)
            bookItem ->{
                val bookId = uri.pathSegments[1]
                db.delete("book","id = ?", arrayOf(bookId))
            }
            categoryDir-> db.delete("book",selection,selectionArgs)
            categoryItem ->{
                val categoryId = uri.pathSegments[1]
                db.delete("category","id = ?", arrayOf(categoryId))
            }
            else -> 0
        }
        deleteRows
    }?:0

    override fun insert(uri: Uri, values: ContentValues?) = dbHelper?.let {
        val db = it.writableDatabase
        val uriReturn = when(uriMatcher.match(uri)){
            bookItem,bookDir ->{
                val newBookId = db.insert("book",null,values)
                Uri.parse("content://$authority/book/$newBookId")
            }
            categoryDir,categoryItem ->{
                val newCategoryId = db.insert("category",null,values)
                Uri.parse("content://$authority/category/$newCategoryId")
            }
            else -> null
        }
        uriReturn
    }

    @SuppressLint("Recycle")
    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    )= dbHelper?.let {
        val db = it.readableDatabase
        val cursor = when(uriMatcher.match(uri)){
            bookDir -> db.query("Book",projection,selection,selectionArgs,null,null,sortOrder)
            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.query("Book",projection,"id = ?", arrayOf(bookId),null,null,sortOrder)
            }
            categoryDir -> db.query("Book",projection,selection,selectionArgs,null,null,sortOrder)
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.query("Book",projection,"id = ?", arrayOf(categoryId),null,null,sortOrder)
            }
            else ->null
        }
        cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ) = dbHelper?.let {
        val db = it.writableDatabase
        val updateRows = when(uriMatcher.match(uri)){
            bookDir -> db.update("book",values,selection,selectionArgs)
            bookItem ->{
                val bookId = uri.pathSegments[1]
                db.update("book",values,"id =?", arrayOf(bookId))
            }
            categoryDir -> db.update("category",values,selection,selectionArgs)
            categoryItem ->{
                val categoryId = uri.pathSegments[1]
                db.update("category",values,"id =?", arrayOf(categoryId))
            }
            else -> 0
        }
        updateRows
    }?:0
}