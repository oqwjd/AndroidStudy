package com.example.studyproject.materialDesign

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyproject.databinding.MaterialCardRecyclerViewBinding
import com.example.studyproject.listViewAndRecycleView.Fruit

class FruitItemsRecyclerAdapter(val context: Context,val fruitList:List<Fruit>) :RecyclerView.Adapter<FruitItemsRecyclerAdapter.ViewHolder>(){

    private lateinit var viewBinder:MaterialCardRecyclerViewBinding

    inner class ViewHolder(viewBinder:MaterialCardRecyclerViewBinding):RecyclerView.ViewHolder(viewBinder.root){
        val fruitImage = viewBinder.MDCardImageView
        val fruitName = viewBinder.MDCardTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MaterialCardRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener{
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            val intent = Intent(context,CollapsingToolbarLayoutActivity::class.java).apply {
                putExtra("fruit_name",fruit.name)
                putExtra("fruit_image_id",fruit.imageId)
            }
            context.startActivity(intent)
        }
        return viewHolder
    }

    override fun getItemCount() = fruitList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fruitImage.setImageResource(fruitList[position].imageId)
        holder.fruitName.text = fruitList[position].name
    }
}