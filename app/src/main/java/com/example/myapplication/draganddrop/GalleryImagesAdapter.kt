package com.example.myapplication.draganddrop


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R



public class GalleryImagesAdapter(private var itemList:List<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            val view: View =
                LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ItemViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        val positionInList: Int = position % itemList.size
        populateItemRows(viewHolder as ItemViewHolder, positionInList)

    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }



    private class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivGallery: ImageView

        init {
            ivGallery = itemView.findViewById(R.id.iv_gallery) as ImageView
        }
    }



    private fun populateItemRows(viewHolder: ItemViewHolder, position: Int) {
        val uri: String = itemList.get(position)

        Glide.with(viewHolder.ivGallery.context).load(uri).into(viewHolder.ivGallery);

    }






}
