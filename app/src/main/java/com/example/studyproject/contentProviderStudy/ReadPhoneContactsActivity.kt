package com.example.studyproject.contentProviderStudy

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityReadPhoneContactsBinding

class ReadPhoneContactsActivity : AppCompatActivity() {

    private var contactsList = ArrayList<PhoneContacts>()
    lateinit var bind : ActivityReadPhoneContactsBinding

    /***
     * 自己的理解：ContextCompat，软件配置的封装，PackageManager，手机配置的封装，ActivityCompat指的是当前activity的配置的集合
     * TODO context和activity还需要深入理解
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_phone_contacts)
        bind = ActivityReadPhoneContactsBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.readPhoneContactsRecycleList.layoutManager = LinearLayoutManager(this)
        bind.readPhoneContactsRecycleList.adapter = ReadPhoneContactsAdapter(contactsList)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),1)
        }else{
            readContacts()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1 ->{
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    readContacts()
                }else{
                    Toast.makeText(this,"you denied the permission",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /***
     * ContactsContract.CommonDataKinds.Phone封装了一些手机的信息
     */
    @SuppressLint("Range", "NotifyDataSetChanged", "Recycle")
    private fun readContacts(){
        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )?.apply {
            while (moveToNext()) {
                val displayName =
                    getString((getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)))
                val number =
                    getString((getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)))
                contactsList.add(PhoneContacts(displayName, number))
            }
            bind.readPhoneContactsRecycleList.adapter?.notifyDataSetChanged()
            close()
        }
    }
}