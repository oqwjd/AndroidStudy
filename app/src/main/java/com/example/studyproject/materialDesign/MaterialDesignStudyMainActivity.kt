package com.example.studyproject.materialDesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityMaterialDesignStudyMainBinding

class MaterialDesignStudyMainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_design_study_main)
        val viewBinder = ActivityMaterialDesignStudyMainBinding.inflate(layoutInflater)
        setContentView(viewBinder.root)
        viewBinder.root.children.forEach { it.setOnClickListener(this) }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.materialDesignExa ->startActivity(Intent(this,MaterialDesignExaActivity::class.java))
        }
    }


}