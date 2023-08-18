package com.example.studyproject.fragmentStudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.studyproject.R
import com.example.studyproject.databinding.FragmentLeftBinding

class FragmentLeft:Fragment(),View.OnClickListener {

    /***
     * 1、_binding是内部使用的binding，同时给外层使用一个binding对象
     * 2、使用三个参数可以优化布局，减少布局层数，加快渲染速度，前提条件是知道LayoutInflater和ViewGroup
     */
    private var _binding: FragmentLeftBinding ?= null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentLeftBinding.inflate(inflater,container,false)
//        _binding =  FragmentLeftBinding.inflate(layoutInflater)
        binding.root.children.forEach { it.setOnClickListener(this) }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.LeftFragmentButton1 -> button1Click()
            R.id.LeftFragmentButton2 -> button2Click()
        }
    }

    private fun button1Click(){
//        Toast.makeText(context,"click button1",Toast.LENGTH_SHORT).show()
        val activity = activity as FragmentStudyMainActivity
        activity.replaceFragment(FragmentRight())

    }

    private fun button2Click(){
        val activity = activity as FragmentStudyMainActivity
        val rightFragment = activity.rightFragment as? FragmentRight
        if (rightFragment ==null){
            val newFragment = FragmentRight()
            (activity as FragmentStudyMainActivity).replaceFragment(newFragment)
            //这个时候甚至还没有进行newFragment的onAttach，不会进行binding的初始化，以下代码会报错
//            newFragment.binding.rightFragmentText.text = "没有点击button1加载Fragment，本次点击先加载"
        }
        else rightFragment.binding.rightFragmentText.text = "改变了内容"
    }
}
