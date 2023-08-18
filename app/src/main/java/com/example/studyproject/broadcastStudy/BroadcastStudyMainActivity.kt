package com.example.studyproject.broadcastStudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityBroadcastStudyMainBinding

class BroadcastStudyMainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_study_main)
        val bind = ActivityBroadcastStudyMainBinding.inflate(layoutInflater);
        setContentView(bind.root)

        bind.root.children.forEach { it.setOnClickListener(this) }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.broadcastTimeChangeReceive -> startActivity(Intent(this,BroadcastTimeChangeActivity::class.java))
            R.id.standardBroadcast -> sendStandardBroadcast()
            R.id.orderedBroadcast -> sendOrderedBroadcast()
        }
    }

    /***
     * intent需要和receiver的action名一致
     * packageName就是项目名，把消息限定在自己的程序内
     */
    private fun sendStandardBroadcast(){
        val intent =Intent("com.example.studyproject.broadcastStudy.MY_STANDARD_BROADCAST")
        intent.setPackage(packageName)
        sendBroadcast(intent)
    }

    /***
     * TODO 第二个参数是receiverPermission，需要了解下有什么用
     * 可以在manifests里面设置优先级
     */
    private fun sendOrderedBroadcast(){
        val intent =Intent("com.example.studyproject.broadcastStudy.MY_STANDARD_BROADCAST")
        intent.setPackage(packageName)
        sendOrderedBroadcast(intent,null)
    }
}