package com.example.studyproject.contentProviderStudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityContentProviderMainBinding

class ContentProviderMainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider_main)
        val bind = ActivityContentProviderMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.root.children.forEach { it.setOnClickListener(this) }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.contentProviderStudyReadPhone -> startActivity(Intent(this,ReadPhoneContactsActivity::class.java))
            R.id.contentProviderStudyNew -> startActivity(Intent(this,CreateContentProviderActivity::class.java))
        }
    }
}