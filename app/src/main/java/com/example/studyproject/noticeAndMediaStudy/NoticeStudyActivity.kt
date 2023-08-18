package com.example.studyproject.noticeAndMediaStudy

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityNoticeStudyBinding


/***
 * TODO 通知还有很多内容可以配置，需要去看文档NotificationCompat
 * 1、getSystemService(Context.NOTIFICATION_SERVICE)  as NotificationManager获取通知的manager
 * 2、新版本有通知channel（可以软件设置是否接收这个channel的通知）：
 * （1）Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O 版本限制
 * （2）channel = NotificationChannel(id,设置时显示的channel名,优先级)
 * （3）manager.createNotificationChannel(channel)
 * 3、发送通知 NotificationCompat.Builder(this,channelId)
 * 点击关闭通知两种方法：onCreate里manager.cancel(IdInNotify)
 * 4、发送短信manager.notify(id,notification)
 * 5、需要权限配置    <uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
 */
class NoticeStudyActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var manager :NotificationManager

    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_study)
        val bind = ActivityNoticeStudyBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.root.children.forEach { it.setOnClickListener(this) }

        manager = getSystemService(Context.NOTIFICATION_SERVICE)  as NotificationManager
        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O){
            val channel = NotificationChannel("normal","Normal", NotificationManager.IMPORTANCE_DEFAULT)
            val channel12 = NotificationChannel("important","Important", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
            manager.createNotificationChannel(channel12)
        }

//        manager.cancel(1)


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.noticeStudyNormal -> sendNormalNotice()
        }
    }

    private fun sendNormalNotice(){
        val intent = Intent(this,NoticeStudyActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(this,"normal")
            .setContentTitle("normal title")
            .setContentText("一般文字，太多会显示为...")
            .setSmallIcon(R.drawable.watermelon_pic)
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.strawberry_pic))
            .setStyle(NotificationCompat.BigTextStyle().bigText("显示更多文字\n" +
                    "显示更多文字\n" +
                    "显示更多文字\n" +
                    "显示更多文字\n" +
                    "显示更多文字\n"))
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(
                BitmapFactory.decodeResource(resources,R.drawable.apple_pic)))
            .setContentIntent(pi)
            .setAutoCancel(true)
//            .setUsesChronometer(true)
            .build()

        val notification12 = NotificationCompat.Builder(this,"important")
            .setContentTitle("important title")
            .setContentText("important text")
            .setContentIntent(pi)
            .setSmallIcon(R.drawable.strawberry_pic)
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.watermelon_pic))
            .setAutoCancel(true)
            .setUsesChronometer(true)
            .build()
        manager.notify(1,notification)
        manager.notify(2,notification12)
    }
}
