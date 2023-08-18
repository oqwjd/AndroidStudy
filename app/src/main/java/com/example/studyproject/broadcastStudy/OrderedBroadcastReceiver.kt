package com.example.studyproject.broadcastStudy

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class OrderedBroadcastReceiver : BroadcastReceiver() {

    /***
     * 都接受一样的广播，但是这个阻断掉了
     * 标准广播可以通过，但是有序广播不行
     */
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,"receive ordered Broadcast", Toast.LENGTH_LONG).show()
        abortBroadcast()
    }
}