package com.example.studyproject.activityStudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityActivityLifecycleBinding

class ActivityLifecycleActivity : AppCompatActivity() ,View.OnClickListener{
    /***
     * activity生命周期
     *
     *               <----------onRestart---------------- 前一个activity无法看到了
     *              |                                   |
     * onCreate->onStart->onResume->runtime->onPause->onStop->onDestroy
     *                      |                   |
     *                       <------------------ 前一个activity仍有部分能看到
     *  TODO manifest android:theme="@style/Theme.AppCompat.Dialog" 此标签需要学习并熟练运用
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity_lifecycle)
        val bind = ActivityActivityLifecycleBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.root.children.forEach { it.setOnClickListener(this) }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.activityLifecycleNormal -> startActivity(Intent(this,ActivityLifecyeleNormalActivity::class.java))
            R.id.activityLifecycleDialog -> startActivity(Intent(this,ActivityLifecyeleDialogActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("ActivityLifecycle","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ActivityLifecycle","onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("ActivityLifecycle","onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ActivityLifecycle","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ActivityLifecycle","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ActivityLifecycle","onDestroy")
    }
}
