package com.example.studyproject.activityStudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityIntentWithMessageBinding

class IntentWithMessageActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var bind :ActivityIntentWithMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_with_message)
        bind = ActivityIntentWithMessageBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.root.children.forEach { it.setOnClickListener(this) }

        /***
         * TODO 第一个参数什么意思暂时没去了解
         * 第二个参数为回调函数，可以用::funName指命回调函数或直接使用匿名函数，自带参数result
         * 此注册方法需要在启动前完成，即onCreate或者OnStart中
         * 无参数返回（不携带参数finish或者使用返回键返回），Result_Code是RESULT_CANCELED，其余情况自己指定
         */
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::readMessage)
        if (savedInstanceState!=null)
            bind.saveInstanceState.setText(savedInstanceState.getString("text","nothing leave"))

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.withoutMessageBack -> withoutMessageBack()
            R.id.withMessageBack1 -> withMessageBack1()
        }
    }

    private fun withoutMessageBack(){
        val intent = Intent(this,IntentMessagePageActivity::class.java)
        val data = Person("san","zhang",55)
        /***
         * 携带自己的类，需要类继承Serializable
         */
        intent.putExtra("data",data)
        startActivity(intent)
    }

    private fun withMessageBack1(){
        /***
         * 在onCreate中launch注册了registerForActivityResult，有返回信息会进入回调函数readMessage中
         */
        val intent = Intent(this,IntentMessagePageActivity::class.java)
        val data = Person("wenbo","xue")
        intent.putExtra("data",data)

        launcher.launch(intent)

    }


    private fun readMessage (result: ActivityResult){

        var data = result.data?.getStringExtra("data")
        Toast.makeText(this,StringBuilder(result.resultCode.toString()+" "+data),Toast.LENGTH_SHORT).show()
    }

    /***
     * 当用户离开Activity时,如果系统由于内存不足需要重建Activity,会调用此方法。
     * 当用户更改设备配置导致Activity被销毁时,如旋转屏幕时。
     * 当按下Home键回到主屏时,当前Activity会被暂停并保存状态。
     * 当前Activity启动模式是SingleTop时,通过启动栈顶相同Activity会引发此回调。
     * 调用finish()并且在Manifest中设置了configChanges时会触发调用。
     * 当内存极低时,也有可能直接回收整个App导致Activity直接终止。
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val text = bind.saveInstanceState.text.toString()
        outState.putString("text",text)
    }

}