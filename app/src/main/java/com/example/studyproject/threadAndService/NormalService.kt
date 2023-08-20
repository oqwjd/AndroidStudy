package com.example.studyproject.threadAndService

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.studyproject.R

class NormalService : Service() {

    private val mBinder = ServiceBinder()

    class ServiceBinder :Binder(){
        fun startBinderFun1(){
            Log.d("NormalService","startBinderFun1")
        }

        fun startBinderFun2(){
            Log.d("NormalService","startBinderFun2")
        }

        fun startBinderFun3(){
            Log.d("NormalService","startBinderFun3")
        }
    }

    /***
     * onCreate中使用类似通知的方式，可以把后台service转变成前台service
     * 需要权限
     */
    override fun onCreate() {
        super.onCreate()
        Log.d("NormalService","onCreate")
//        以下代码转变为前台service
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("fore_service","前台service",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this,ServiceStudyActivity::class.java)
        val pi = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(this,"fore_service")
            .setContentTitle("FORE_SERVICE")
            .setContentText("this is fore service")
            .setSmallIcon(R.drawable.apple_pic)
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.strawberry_pic))
            .setContentIntent(pi)
            .setUsesChronometer(true)
            .build()
        startForeground(1,notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("NormalService","onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d("NormalService","onDestroy")
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d("NormalService","onBind")
        return mBinder
    }
}