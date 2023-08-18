package com.example.studyproject.noticeAndMediaStudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityNoticeAndMediaStudyMainBinding

class NoticeAndMediaStudyMainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_and_media_study_main)
        val bind = ActivityNoticeAndMediaStudyMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.root.children.forEach { it.setOnClickListener(this) }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.noticeAndMediaStudyNotice -> startActivity(Intent(this,NoticeStudyActivity::class.java))
            R.id.noticeAndMediaStudyPicture -> startActivity(Intent(this,PictureStudyActivity::class.java))
            R.id.noticeAndMediaStudyMedia -> startActivity(Intent(this,MediaStudyActivity::class.java))
            R.id.cameraXStudy-> startActivity(Intent(this,CameraXActivity::class.java))
        }
    }
}