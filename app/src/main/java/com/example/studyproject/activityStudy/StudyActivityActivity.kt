package com.example.studyproject.activityStudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityStudyActivityBinding

class StudyActivityActivity : AppCompatActivity(),View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_activity)
        val bind = ActivityStudyActivityBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.root.children.forEach { it.setOnClickListener(this) }
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.intentStudy ->  startActivity(Intent(this,IntentStudyActivity::class.java))
            R.id.activityLifecycle -> startActivity(Intent(this,ActivityLifecycleActivity::class.java))
        }
    }
}