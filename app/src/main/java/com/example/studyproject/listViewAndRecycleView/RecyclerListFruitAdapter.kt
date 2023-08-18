package com.example.studyproject.listViewAndRecycleView

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.studyproject.databinding.FruitItemLayoutBinding

class RecyclerListFruitAdapter(private val fruitList:List<Fruit>):RecyclerView.Adapter<RecyclerListFruitAdapter.ViewHolder>() {
    inner class ViewHolder(bind :FruitItemLayoutBinding) : RecyclerView.ViewHolder(bind.root){
        val fruitImage = bind.fruitImage
        val fruitName = bind.fruitText
    }

//    inner class ViewHolder(view :View) : RecyclerView.ViewHolder(view){
//        val fruitImage = view.findViewById<ImageView>(R.id.fruitImage)
//        val fruitName = view.findViewById<TextView>(R.id.fruitText)
//
//    }

    /***
     * 此处的返回类型需要时自己定义的ViewHolder，重写onBindViewHolder的时候才能强转
     * viewHolder.adapterPosition和viewHolder.layoutPosition效果一致，都是指当前位置的position
     * TODO 为什么创建了自己的ViewHolder，可以直接从当中拿到position，目前看是因为继承了RecyclerView.ViewHolder，当中有offsetPosition修改了mPosition导致的
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item_layout,parent,false)
//        val viewHolder = ViewHolder(view)
        val bind = FruitItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val viewHolder = ViewHolder(bind)
        viewHolder.fruitImage.setOnClickListener{
            Toast.makeText(parent.context,"Click FruitImage, Position:${viewHolder.adapterPosition}、${viewHolder.layoutPosition}",Toast.LENGTH_SHORT).show()
        }
        viewHolder.fruitName.setOnClickListener{
            Toast.makeText(parent.context,"Click fruitName, Position:${viewHolder.adapterPosition}、${viewHolder.layoutPosition}",Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitName.text = fruit.name
        holder.fruitImage.setImageResource(fruit.imageId)
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

}