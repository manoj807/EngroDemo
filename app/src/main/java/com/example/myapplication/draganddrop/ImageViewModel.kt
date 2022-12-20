package com.example.myapplication.draganddrop

import android.app.Application
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.AppController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ImageViewModel @Inject constructor() : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private var imagesLiveData: MutableLiveData<List<String>?> = MutableLiveData()
    fun getImageList(): MutableLiveData<List<String>?> {
        return imagesLiveData
    }


    internal fun loadImagesfromSDCard(): ArrayList<String> {
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val cursor: Cursor?
        val column_index_data: Int
        //val column_index_folder_name: Int
        val listOfAllImages = ArrayList<String>()


        val projection = arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

        cursor = AppController.getInstance().contentResolver.query(uri, projection, null, null, null)

        cursor?.let {
            column_index_data = it.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            //column_index_folder_name = it.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            while (it.moveToNext()) {
                var absolutePathOfImage= it.getString(column_index_data)
                listOfAllImages.add(absolutePathOfImage)
            }
        }

        return listOfAllImages
    }

    fun getAllImages() {
        launch(Dispatchers.Main) {
            imagesLiveData.value = withContext(Dispatchers.IO) {
                loadImagesfromSDCard()
            }
        }
    }
}