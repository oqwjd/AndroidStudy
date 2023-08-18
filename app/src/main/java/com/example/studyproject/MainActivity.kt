package com.example.studyproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.children
import com.example.studyproject.databinding.ActivityMainBinding
import com.example.studyproject.activityStudy.StudyActivityActivity
import com.example.studyproject.broadcastStudy.BroadcastStudyMainActivity
import com.example.studyproject.contentProviderStudy.ContentProviderMainActivity
import com.example.studyproject.dataSaveStudy.DataSaveStudyMainActivity
import com.example.studyproject.fragmentStudy.FragmentStudyMainActivity
import com.example.studyproject.layoutStudy.LayoutStudyMainActivity
import com.example.studyproject.listViewAndRecycleView.ListViewAndRecycleViewMainActivity
import com.example.studyproject.noticeAndMediaStudy.NoticeAndMediaStudyMainActivity

class MainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /***
         * 1、使用binding需要在Module级build.gradle里进行以下配置
         * buildFeatures {
         *     viewBinding true
         * }
         * 2、bind不能提前加载，如果要使用全局变量的话，需要使用lateinit var bind ： Bind的方式
         */
        val mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainBinding.root.children.forEach { it.setOnClickListener(this) }


    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.activityStudy ->  startActivity(Intent(this,StudyActivityActivity::class.java))
            R.id.layoutStudy ->  startActivity(Intent(this,LayoutStudyMainActivity::class.java))
            R.id.listViewAndRecyclerViewStudy -> startActivity(Intent(this,ListViewAndRecycleViewMainActivity::class.java))
            R.id.fragmentStudy -> startActivity(Intent(this,FragmentStudyMainActivity::class.java))
            R.id.broadcastStudy -> startActivity(Intent(this,BroadcastStudyMainActivity::class.java))
            R.id.dataSaveStudy -> startActivity(Intent(this,DataSaveStudyMainActivity::class.java))
            R.id.contentProviderStudy -> startActivity(Intent(this,ContentProviderMainActivity::class.java))
            R.id.noticeAndMedia -> startActivity(Intent(this, NoticeAndMediaStudyMainActivity::class.java))
        }
    }

}