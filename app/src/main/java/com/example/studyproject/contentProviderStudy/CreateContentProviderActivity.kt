package com.example.studyproject.contentProviderStudy

import android.annotation.SuppressLint
import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityCreateContentProviderBinding


//TODO {
// 仍然需要学习这部分的内容
// 1、记住各个方法的固定格式
// 2、同小结权限问题
// 3、ContextCompat，PackageManager，ActivityCompat
// 4、context、activity概念
// }
class CreateContentProviderActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var bind : ActivityCreateContentProviderBinding
    private val authority = "com.example.studyproject.provider"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_content_provider)

        bind = ActivityCreateContentProviderBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.root.children.forEach { it.setOnClickListener(this) }

    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.myContentProviderAdd -> addBook()
            R.id.myContentProviderUpgrade ->upgradeBook()
            R.id.myContentProviderSearch -> searchBook()
            R.id.myContentProviderDelete -> deleteBook()
        }
    }

    private fun addBook(){
        val addData = bind.myContentProviderAddBook.text.split(":")
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
            val uri = Uri.parse("content://$authority/book")
            contentResolver.insert(uri,value)
        }
        bind.myContentProviderAddBook.setText("")
    }

    private fun upgradeBook(){
        val editorData = bind.myContentProviderChange.text.split(",")
        val value = ContentValues().apply{
            for (i in 0 until editorData[0].toInt()){
                val kv = editorData[i+2].split(':')
                put(kv[0],kv[1])
            }
        }

        val uri = Uri.parse("content://$authority/book/${editorData[1]}")
        contentResolver.update(uri,value,null,null)
    }


    @SuppressLint("Range", "Recycle")
    private fun searchBook(){
        val editorData = bind.myContentProviderSearchData.text.toString()

        val uri = Uri.parse("content://$authority/book")
        val cursor = contentResolver.query(uri,null,if(editorData == "") null else editorData,null,null)
        val text = StringBuilder()
        if (cursor != null)
            if(cursor.moveToFirst()){
                do {
                    text.append("id:${cursor.getInt(cursor.getColumnIndex("id"))} " +
                            "name:${cursor.getString(cursor.getColumnIndex("name"))} " +
                            "name:${cursor.getString(cursor.getColumnIndex("author"))} " +
                            "pages:${cursor.getInt(cursor.getColumnIndex("pages"))} " +
                            "price:${cursor.getDouble(cursor.getColumnIndex("price"))} " +
                            "category_id:${cursor.getInt(cursor.getColumnIndex("category_id"))}\n")
                    text
                }while (cursor.moveToNext())
            }
        bind.myContentProviderShow.text = text.toString()
    }

    private fun deleteBook(){
        val editorData = bind.myContentProviderDeleteData.text
        val uri = Uri.parse("content://$authority/book")
        contentResolver.delete(uri,editorData.toString(),null)
    }

}