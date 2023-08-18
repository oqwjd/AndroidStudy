package com.example.studyproject.listViewAndRecycleView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityRecycleListBinding

class RecyclerListActivity : AppCompatActivity() {
    private var fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_list)
        val bind = ActivityRecycleListBinding.inflate(layoutInflater)
        setContentView(bind.root)

        initFruits()
        bind.recyclerLayout.layoutManager = LinearLayoutManager(this)
        bind.recyclerLayout.adapter = RecyclerListFruitAdapter(fruitList)
//        每行三个元素
//        bind.recyclerLayout.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
//        改成横向滑动
//        (bind.recyclerLayout.layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL
    }

    private fun initFruits(){
        repeat(4){
            fruitList.add(Fruit("apple",R.drawable.apple_pic))
            fruitList.add(Fruit("orange",R.drawable.orange_pic))
            fruitList.add(Fruit("strawberry",R.drawable.strawberry_pic))
            fruitList.add(Fruit("watermelon",R.drawable.watermelon_pic))
        }
    }
}