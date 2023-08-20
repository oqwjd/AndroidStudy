package com.example.studyproject.threadAndService

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityThreadAndServiceMainBinding

class ThreadAndServiceMainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_and_service_main)
        val viewBinder = ActivityThreadAndServiceMainBinding.inflate(layoutInflater)
        setContentView(viewBinder.root)
        viewBinder.root.children.forEach { it.setOnClickListener(this) }

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.threadStudy -> startActivity(Intent(this,ThreadStudyActivity::class.java))
            R.id.serviceStudy -> startActivity(Intent(this,ServiceStudyActivity::class.java))
        }
    }
}