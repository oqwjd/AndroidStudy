package com.example.studyproject.dataSaveStudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityDataSaveStudyMainBinding

class DataSaveStudyMainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_save_study_main)
        val bind = ActivityDataSaveStudyMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.root.children.forEach{ it.setOnClickListener(this)}

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.openFileSave -> startActivity(Intent(this,OpenFileSaveActivity::class.java))
            R.id.sharePreferenceSave -> startActivity(Intent(this,SharePreferenceSaveActivity::class.java))
            R.id.SQLiteSave -> startActivity(Intent(this,SQLiteSaveActivity::class.java))
        }
    }
}