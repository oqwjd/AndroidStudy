package com.example.studyproject.listViewAndRecycleView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityListViewAndRecycleViewMainBinding

class ListViewAndRecycleViewMainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_and_recycle_view_main)
        val bind = ActivityListViewAndRecycleViewMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.root.children.forEach { it.setOnClickListener(this) }

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ListView -> startActivity(Intent(this,ListViewActivity::class.java))
            R.id.RecycleView -> startActivity(Intent(this,RecyclerListActivity::class.java))
        }
    }
}