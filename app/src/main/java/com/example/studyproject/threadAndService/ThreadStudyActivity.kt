package com.example.studyproject.threadAndService

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityThreadStudyBinding
import java.lang.Exception
import kotlin.concurrent.thread

class ThreadStudyActivity : AppCompatActivity() {
    private lateinit var viewBinder:ActivityThreadStudyBinding


    // TODO 学习使用协程
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_study)
        viewBinder = ActivityThreadStudyBinding.inflate(layoutInflater)
        setContentView(viewBinder.root)

        viewBinder.threadOne.setOnClickListener{threadOneOnClick()}
        viewBinder.threadTwo.setOnClickListener{
            Toast.makeText(this,"AsyncTask已经被弃用，学习使用协程",Toast.LENGTH_SHORT).show()}

    }

    @SuppressLint("SetTextI18n")
    inner class ThreadType2:Thread(){
        override fun run() {
            Log.d("threadTest","Thread type2 run")
            super.run()
        }
    }

    @SuppressLint("SetTextI18n")
    inner class ThreadType3:Runnable{
        override fun run() {
            Log.d("threadTest","Thread type3 run")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun threadOneOnClick(){
        viewBinder.threadTextView.text = "main started"
        Log.d("threadTest","main started")

        thread{
            for (i in 0..10){
                try {
                    val msg = Message()
                    msg.what = i
                    handler1.sendMessage(msg)
                }catch (e:Exception){
                    Log.d("handlerTest","handler1 cannot change view")
                    val msg = Message()
                    msg.what = i
                    handler2.sendMessage(msg)
                }
            }
        }
        viewBinder.threadTextView.text = "${viewBinder.threadTextView.text}\nmain center"
        Log.d("threadTest","main main center")
        ThreadType2().start()
        Thread(ThreadType3()).start()
        viewBinder.threadTextView.text = "${viewBinder.threadTextView.text}\nmain ended"
        Log.d("threadTest","main main ended")

    }

    /***
     * 页面的跟新，必须在主线程里面进行
     * Looper.myLooper()!!当前线程，非主线程
     * Looper.getMainLooper()主线程
     */
    private val handler1 = object: Handler(Looper.myLooper()!!){
        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message) {
            Log.d("threadTest","handler1:${msg.what}")
            viewBinder.threadTextView.text = "${viewBinder.threadTextView.text}\nhandler1:${msg.what}"
            super.handleMessage(msg)
        }
    }
    private val handler2 = object : Handler(Looper.getMainLooper()) {
        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message) {
            Log.d("threadTest","handler2:${msg.what}")
            viewBinder.threadTextView.text = "${viewBinder.threadTextView.text}\nhandler2:${msg.what}"
            super.handleMessage(msg)
        }
    }

}