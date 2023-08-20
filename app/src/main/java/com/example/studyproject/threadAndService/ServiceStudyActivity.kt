package com.example.studyproject.threadAndService

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityServiceStudyBinding

/***
 * 创建service，先进行onCreate、onStartCommand
 * 绑定service，先进性onBind，获取bind对象，然后才会进行onServiceConnected
 *此时可以通过bind调用service的方法了
 * 要解除bind后，service才会进行destroy
 */
class ServiceStudyActivity : AppCompatActivity() {

    lateinit var serviceBinder:NormalService.ServiceBinder

    private val connection = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d("NormalService","onServiceConnectStart")
            serviceBinder = service as NormalService.ServiceBinder
            Log.d("NormalService","onServiceConnectEnd")
            serviceBinder.startBinderFun1()

        }

        override fun onServiceDisconnected(name: ComponentName?) {
            serviceBinder.startBinderFun2()
            Log.d("NormalService","onServiceDisconnected")
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_study)
        val viewBinder = ActivityServiceStudyBinding.inflate(layoutInflater)
        setContentView(viewBinder.root)

        viewBinder.serviceStudyStartService.setOnClickListener{
            val intent = Intent(this,NormalService::class.java)
            startService(intent)
        }

        viewBinder.serviceStudyStopService.setOnClickListener{
            val intent = Intent(this,NormalService::class.java)
            stopService(intent)
        }

        viewBinder.serviceStudyBindService.setOnClickListener{
            val intent = Intent(this,NormalService::class.java)
            bindService(intent,connection,Context.BIND_AUTO_CREATE)
        }

        viewBinder.serviceStudyUnbindService.setOnClickListener{
            val intent = Intent(this,NormalService::class.java)
            unbindService(connection)
        }

        viewBinder.serviceStudyServiceFun3.setOnClickListener{
            serviceBinder.startBinderFun3()
        }

    }
}