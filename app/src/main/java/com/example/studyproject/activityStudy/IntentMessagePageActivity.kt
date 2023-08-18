package com.example.studyproject.activityStudy

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityIntentMessagePageBinding
import java.io.Serializable
import java.lang.StringBuilder

class IntentMessagePageActivity : AppCompatActivity() ,View.OnClickListener{

    private lateinit var bind:ActivityIntentMessagePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_message_page)
        bind = ActivityIntentMessagePageBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.root.children.forEach { it.setOnClickListener(this) }

        /***
         * getSerializableExtra(key,class)在33版本才能用，getSerializableExtra(key)在33版本废弃了，所以需要进行一下版本映射
         * Build.VERSION.SDK_INT 当前版本
         * Build.VERSION_CODES.TIRAMISU，接口说明中指定版本
         */
        var data = intent.getSerializable("data",Person::class.java)
        bind.intentMessagePageMessage.text = StringBuilder(data.lastname+" "+data.firstname+" 年龄:"+data.age).toString()

    }

    private fun <T : Serializable?> Intent.getSerializable(key: String, m_class: Class<T>): T {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            this.getSerializableExtra(key, m_class)!!
        else
            this.getSerializableExtra(key) as T
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.intentMessagePageButtonWithoutMessage -> finish()
            R.id.intentMessagePageButtonWithMessage1 ->{clickBackButton(1)}
            R.id.intentMessagePageButtonWithMessage2 ->{clickBackButton(2)}
        }
    }

    private fun clickBackButton(id :Int){
        when(id){
            1->setResult(RESULT_OK,Intent().putExtra("data","click BackButton1"))
            2->setResult(RESULT_OK,Intent().putExtra("data","click BackButton2"))
        }
        finish()
    }


}