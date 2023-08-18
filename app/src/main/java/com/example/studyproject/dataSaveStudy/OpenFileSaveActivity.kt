package com.example.studyproject.dataSaveStudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityOpenFileSaveBinding
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder

class OpenFileSaveActivity : AppCompatActivity(), View.OnClickListener{
    lateinit var bind : ActivityOpenFileSaveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_file_save)
        bind = ActivityOpenFileSaveBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.root.children.forEach { it.setOnClickListener(this) }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.openFileSaveAdd -> save(MODE_APPEND)
            R.id.openFileSaveReplace -> save(MODE_PRIVATE)
            R.id.openFileSaveShow -> loadData()
            R.id.openFileSaveCopy -> copyText()
        }
    }

    private fun save(saveType:Int){
        try{
            val file = openFileOutput("openFileData",saveType)
            val writer = BufferedWriter(OutputStreamWriter(file))
            writer.use {
                it.write(bind.openFileSaveWrite.text.toString()?:" ")
            }
        }catch (e:IOException){
            e.printStackTrace()
        }
    }

    private fun loadData(){
        val content = StringBuilder()
        try {
            val file = openFileInput("openFileData")
            val reader = BufferedReader(InputStreamReader(file))
            reader.use {
                reader.forEachLine { content.append(it) }
            }
        }catch (e:IOException){
            e.printStackTrace()
        }
        bind.openFileSaveRead.text = content.toString()
    }

    private fun copyText(){
        bind.openFileSaveWrite.setText(bind.openFileSaveRead.text.toString()?:"")
    }
}