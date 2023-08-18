package com.example.studyproject.listViewAndRecycleView

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.studyproject.R

/***
 * private val resourceId:Int,不加前缀只有构造体能看见这个变量
 */
class ListViewFruitAdapter(activity: Activity, private val resourceId:Int, data:List<Fruit>) :ArrayAdapter<Fruit>(activity,resourceId,data) {
    inner class ViewHolder(val fruitImage:ImageView,val fruitName:TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view : View
        val viewHolder : ViewHolder
        /***
         * 为空时
         * 先加载了单个list的界面，再用fruitImage和fruitName指向这个界面的对应元素
         * view.tag可以用来存放一些信息，自己推测的return后的View将会成为以后的convertView
         * TODO 验证return后的View将会成为以后的convertView
         * 不会空，viewHolder = view.tag ，地址相同，后续viewHolder也是view.tag
         */
        if (convertView == null){

            view = LayoutInflater.from(context).inflate(resourceId,parent,false)
            val fruitImage :ImageView = view.findViewById<ImageView>(R.id.fruitImage)
            val fruitName :TextView = view.findViewById<TextView>(R.id.fruitText)
            viewHolder = ViewHolder(fruitImage, fruitName)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val fruit = getItem(position)
        /***
         * fruit定义原因需要判空，实际不可能为空
         * 将资源放进viewHolder里面，实际viewHolder就是view.tag
         */
        if (fruit != null){
            viewHolder.fruitName.text = fruit.name
            viewHolder.fruitImage.setImageResource(fruit.imageId)
        }

        return view
    }

}