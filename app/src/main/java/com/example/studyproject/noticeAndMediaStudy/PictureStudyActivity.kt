package com.example.studyproject.noticeAndMediaStudy

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.view.children
import com.example.studyproject.R
import com.example.studyproject.databinding.ActivityPictureStudyBinding
import java.io.File

class PictureStudyActivity : AppCompatActivity(),View.OnClickListener {

    private val takePhoto = 1
    private val fromAlbum = 2
    private lateinit var imageUri :Uri
    private lateinit var outputImage: File
    private lateinit var launcher1 : ActivityResultLauncher<Intent>
    private lateinit var launcher2 : ActivityResultLauncher<Intent>
    private lateinit var bind :ActivityPictureStudyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_study)
        bind = ActivityPictureStudyBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.root.children.forEach { it.setOnClickListener(this) }

        launcher1 = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::takePhotoCallBack)
        launcher2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::fromAlbumCallBack)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.takePhotos -> takePhoneButton()
            R.id.fromAlbum -> fromAlbum()
        }
    }

    /***
     * 在这个activity的缓存目录新建文件，这样不需要额外的权限
     * 如果是低版本，直接使用物理地址，否则使用特殊的contentProvider提供的封装了的地址
     */
    private fun takePhoneButton(){
        outputImage = File(externalCacheDir,"output_image.jpg")
        if(outputImage.exists()) outputImage.delete()
        outputImage.createNewFile()
        imageUri = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            FileProvider.getUriForFile(this,"com.example.studyproject.noticeAndMediaStudy.provider",outputImage)
        }else{
            Uri.fromFile(outputImage)
        }
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
        intent.putExtra("tag",11)
        launcher1.launch(intent)
    }

    private fun fromAlbumCallBack(result:ActivityResult){
        if (result.resultCode == Activity.RESULT_OK){
            result.data?.data.let{ uri->
                val bitmap = uri?.let { getBitmapFromUri(it) }
                bind.photoView.setImageBitmap(bitmap)
            }
        }
    }

    private fun getBitmapFromUri(uri:Uri) = contentResolver.openFileDescriptor(uri,"r")?.use{
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)

    }

    private fun takePhotoCallBack(result:ActivityResult){
        if (result.resultCode == Activity.RESULT_OK){
            val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
            bind.photoView.setImageBitmap(rotateIfRequired(bitmap))
        }
    }

    private fun rotateIfRequired(bitmap:Bitmap):Bitmap{
        val exif = ExifInterface(outputImage.path)
        val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL)
        return when(orientation){
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap,90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap,180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap,270)
            else -> bitmap
        }
    }

    private fun rotateBitmap(bitmap: Bitmap,degree:Int) :Bitmap{
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.width,bitmap.height,matrix,true)
        bitmap.recycle()
        return rotatedBitmap
    }

    private fun fromAlbum(){
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        launcher2.launch(intent)

    }
}