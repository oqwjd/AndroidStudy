package com.example.studyproject.dataSaveStudy

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivitySharePreferenceSaveBinding

class SharePreferenceSaveActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var bind : ActivitySharePreferenceSaveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_preference_save)
        bind = ActivitySharePreferenceSaveBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.root.children.forEach { it.setOnClickListener(this) }
        readAll()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.sharePreferenceSaveAdd -> add()
            R.id.sharePreferenceSaveDelete -> delete()
            R.id.sharePreferenceSaveClear -> clear()

        }
    }

    /***
     * 官方把getSharedPreferences()需要的editor、apply集成为高级函数edit了，需要引用对应的包
     */
    private fun add(){
        val data : List<String> = bind.sharePreferenceSaveInput.text.split(":")
        if (data.size == 2){
            val editor = getSharedPreferences("sharePreferenceData", MODE_PRIVATE).edit{
                putString(data[0],data[1])
            }
            bind.sharePreferenceSaveInput.setText("")
        }else{
            Toast.makeText(this,"格式错误",Toast.LENGTH_SHORT).show()
        }
        readAll()
    }

    private fun delete(){
        val input = bind.sharePreferenceSaveInput.text.toString()
        val data = getSharedPreferences("sharePreferenceData", MODE_PRIVATE)
        if (data.contains(input))
            data.edit().apply(){
            remove(input)
            apply()
        }else{
            Toast.makeText(this,"无此内容",Toast.LENGTH_SHORT).show()
        }
        bind.sharePreferenceSaveInput.setText("")
        readAll()
    }

    private fun clear(){
        val editor = getSharedPreferences("sharePreferenceData", MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
        bind.sharePreferenceSaveInput.setText("")
        bind.sharePreferenceSaveShow.text = ""
    }

    private fun readAll(){
        val perfData = getSharedPreferences("sharePreferenceData", MODE_PRIVATE)
        val allData =  perfData.all
        val dataBuilder = StringBuilder()
        allData.forEach{
            dataBuilder.append(it.key+":"+it.value+"\n")
        }

        bind.sharePreferenceSaveShow.text = dataBuilder.toString()
    }

}