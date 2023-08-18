package com.example.studyproject.activityStudy

import android.app.Application.ActivityLifecycleCallbacks
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityIntentStudyBinding
import com.example.studyproject.databinding.ActivityIntentWithMessageBinding

class IntentStudyActivity : AppCompatActivity() , View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_study)
        val bind = ActivityIntentStudyBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.root.children.forEach { it.setOnClickListener(this) }

    }




    /***
     * 1、res下新建文件夹命名为menu
     * 2、menu文件下新建menu的XML文件，并编写样式
     * 3、新增menu的页面添加以下样式
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.intent1 -> intent1Click()
            R.id.intent2 -> intent2Click()
            R.id.intent3 -> intent3Click()
            R.id.intent4 -> intent4Click()
            R.id.intent5 -> startActivity(Intent(this,IntentWithMessageActivity::class.java))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /***
         * menu按钮点击的逻辑
         */
        when(item?.itemId){
            R.id.add -> Toast.makeText(this,"click menu add",Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this,"click menu delete",Toast.LENGTH_SHORT).show()
            R.id.others -> Toast.makeText(this,"click menu others",Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun intent1Click(){
        /***
         * 显式跳转
         * 1、startActivity需要context才能使用，在自己创立的工具类中需要Context.startActivity来进行调用
         * TODO 了解startActivity逻辑
         * 2、显式调用需要指定Intent，参数为Context和对应的java文件
         * TODO packageContext的package概念不清楚，后续需要学习了解
         */
        val intent = Intent(this,Intent1Activity::class.java)
        startActivity(intent)
    }

    private fun intent2Click(){
        /***
         * 隐式跳转
         * 1、AndroidManifest文件需要跳转的文件声明下增加intent-filter标签，并在标签中增加action、category标间指定name
         * TODO 不是很了解标签，深入了解的话需要学习
         * 2、使用category标间指定名进行跳转
         * 3、不能有data标签，会直接崩溃
         */
        val intent = Intent("android.intent.action.intent1")
        startActivity(intent)
    }

    private fun intent3Click(){
        /***
         * 隐式跳转 网页跳转
         * 1、可以在intent-filter标签内增加data标签，必须和data标签指定的内容匹配才能跳转
         * TODO 实际没有，需要了解原因
         */
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://www.bilibili.com")
        startActivity(intent)
    }

    private fun intent4Click(){
        /***
         * 拨号界面跳转
         * TODO Intent.ACTION_ 下面应该还有很多内容可以了解一下
         */
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:10086")
        startActivity(intent)
    }
}