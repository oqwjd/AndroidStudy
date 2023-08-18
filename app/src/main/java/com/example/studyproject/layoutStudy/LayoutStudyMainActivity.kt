package com.example.studyproject.layoutStudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityLayoutStudyMainBinding

class LayoutStudyMainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_study_main)
        val bind = ActivityLayoutStudyMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.root.children.forEach { it.setOnClickListener(this) }

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.LinearLayout -> startActivity(Intent(this,LinearLayoutActivity::class.java))
            R.id.RelativeLayout -> startActivity(Intent(this,RelativeLayoutActivity::class.java))
            R.id.AbsoluteLayout -> startActivity(Intent(this,AbsoluteLayoutActivity::class.java))
            R.id.FrameLayout -> startActivity(Intent(this,FrameLayoutActivity::class.java))
            R.id.GridLayout -> startActivity(Intent(this,GridLayoutActivity::class.java))
            R.id.ConstraintLayout -> startActivity(Intent(this,ConstraintLayoutActivity::class.java))
        }
    }
}