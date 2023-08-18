package com.example.studyproject.fragmentStudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.studyproject.R

class FragmentStudyMainActivity : AppCompatActivity(){
    var rightFragment : Fragment ?= null

    /***
     * TODO 1、限定符实现单双面 2、判断旋转后通过修改页面实现单双面 3、ConstraintLayout+Guideline实现单双面
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_study_main)
        /***
         * 1、可以通过supportFragmentManager找到Fragment对象来进行点击事件，或者传递信息
         * 2、可以在Fragment里通过getActivity来获取activity的方法传递信息
         */
//        val leftFragment = supportFragmentManager.findFragmentById(R.id.leftFragment) as FragmentLeft
//        leftFragment.binding.LeftFragmentButton1.setOnClickListener{
//            Toast.makeText(this,"click button1", Toast.LENGTH_SHORT).show()
//        }

    }

    fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.rightFrameLayout,fragment)
        transaction.commit()
        rightFragment = fragment
    }
}