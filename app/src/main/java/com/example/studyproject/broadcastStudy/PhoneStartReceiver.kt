package com.example.studyproject.broadcastStudy

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.Toast

class PhoneStartReceiver : BroadcastReceiver() {
    /***
     * 静态获取系统广播，可以在软件未启动的时候接受到广播
     * 1、不需要activity，可以使用new->other->Broadcast Receiver新建
     * 2、创建部分接收器会设计用户隐私的问题，需要在manifests里面进行注释，以获得用户权限
     */
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Toast.makeText(context,"Boot complete",Toast.LENGTH_LONG).show()
    }
}