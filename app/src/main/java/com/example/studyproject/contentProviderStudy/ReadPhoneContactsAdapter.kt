package com.example.studyproject.contentProviderStudy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyproject.databinding.RecyclelistReadPhoneContactsBinding

class ReadPhoneContactsAdapter(private val phoneContactsList: ArrayList<PhoneContacts>) : RecyclerView.Adapter<ReadPhoneContactsAdapter.ViewHolder>() {

    inner class ViewHolder(bind: RecyclelistReadPhoneContactsBinding):RecyclerView.ViewHolder(bind.root){
        val name = bind.phoneContactsName
        val number = bind.phoneContactsNumber
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bind = RecyclelistReadPhoneContactsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(bind)
    }

    override fun getItemCount() = phoneContactsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = phoneContactsList[position].name
        holder.number.text = phoneContactsList[position].number
    }
}