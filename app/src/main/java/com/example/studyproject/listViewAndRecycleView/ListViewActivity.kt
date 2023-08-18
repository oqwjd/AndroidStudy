package com.example.studyproject.listViewAndRecycleView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityListViewBinding

class ListViewActivity : AppCompatActivity() {
    private var fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        val bind = ActivityListViewBinding.inflate(layoutInflater)
        setContentView(bind.root)
        initFruits()
        bind.listViewLayout.adapter = ListViewFruitAdapter(this,R.layout.fruit_item_layout,fruitList)
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