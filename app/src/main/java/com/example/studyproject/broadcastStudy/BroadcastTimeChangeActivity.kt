package com.example.studyproject.broadcastStudy

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import com.example.studyproject.R

class BroadcastTimeChangeActivity : AppCompatActivity() {

    lateinit var timeChangeReceiver : TimeChangeReceiver

    inner class TimeChangeReceiver :BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context,"Time has changed",Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_time_change)
        /***
         * TODO IntentFilter()可以有参数，了解有无参数的区别
         * TODO 系统还有很多其他的广播类型，在<SDK>/platforms/<api>/data/broadcast_actions.txt文档里，有空去了解一下.也可以在manifests里面输入，查看文档的解释
         */
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK") //一分钟一次
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver,intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }
}