package com.example.studyproject.layoutStudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityConstraintLayoutBinding
import java.util.Random

class ConstraintLayoutActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var bind : ActivityConstraintLayoutBinding
    val drawable = listOf(R.drawable.apple_pic,R.drawable.orange_pic,R.drawable.watermelon_pic,R.drawable.strawberry_pic)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout)
        bind = ActivityConstraintLayoutBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.root.children.forEach{ it.setOnClickListener(this) }
        /***
         * 嵌套layout会导致下面的点击事件无法被获取到，即使里层的layout有id也无法获取
         * TODO时间允许的话研究下怎么上传冒泡事件
         */
        bind.addProgress10.setOnClickListener{
            addProgress(10)
        }
        bind.addProgress20.setOnClickListener{
            addProgress(20)
        }

    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.constraintLayoutButton -> bind.showConstraintTextViewWords.text = bind.constraintLayoutEditText.text
            R.id.constraintLayoutImageView -> bind.constraintLayoutImageView.setImageResource(drawable[(0..3).random()])
            /***
             * 创建AlertDialog
             * dialog指代创建的弹框，which区分点击的哪个按钮
             * TODO 应该会经常用，研究下还有哪些参数
             */
            R.id.constraintAlertDialog -> AlertDialog.Builder(this)
                .setTitle("This is Alert Dialog")
                .setMessage("show something important")
                .setCancelable(false)
                .setPositiveButton("OK"){dialog,which->
                    Toast.makeText(this,which.toString(),Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel"){dialog,which->
                    Toast.makeText(this,which.toString(),Toast.LENGTH_SHORT).show()
                }
                .show()



        }
    }

    private fun addProgress(v : Int){
        /***
         * 只有horizontal的progressBar有进度
         */
        bind.constraintProgressBarHorizontal.apply {
            if (visibility == View.GONE){
                progress = 0
                visibility = View.VISIBLE
            }else when(progress){
                in 0..99 -> progress += v
                else -> visibility = View.GONE
            }
        }

        bind.constraintProgressBar.apply {
            visibility = bind.constraintProgressBarHorizontal.visibility
            progress = bind.constraintProgressBarHorizontal.progress
        }


    }




}