package com.example.studyproject.materialDesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityCollapsingToolbarLayoutBinding

class CollapsingToolbarLayoutActivity : AppCompatActivity() {

    companion object{
        const val FRUIT_NAME = "fruit_name"
        const val FRUIT_VIEW = "fruit_image_id"
    }

    private lateinit var viewBinder: ActivityCollapsingToolbarLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collapsing_toolbar_layout)
        viewBinder = ActivityCollapsingToolbarLayoutBinding.inflate(layoutInflater)
        setContentView(viewBinder.root)
        setSupportActionBar(viewBinder.CTToolbar)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
        }
        val fruitName = intent.getStringExtra(FRUIT_NAME)
        val fruitImageId = intent.getIntExtra(FRUIT_VIEW,0)
        viewBinder.CTCToolbar.title = fruitName
        viewBinder.CTImageView.setImageResource(fruitImageId)
        viewBinder.CTTextView.text = fruitName?.repeat(50)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}