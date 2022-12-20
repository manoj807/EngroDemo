package com.example.myapplication


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.List


public class RecyclerViewCircularAdapter(private var itemList:MutableList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            val view: View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
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
        var tvItem: TextView

        init {
            tvItem = itemView.findViewById(R.id.tvItem)
        }
    }



    private fun populateItemRows(viewHolder: ItemViewHolder, position: Int) {
        val item: String = itemList.get(position)
        viewHolder.tvItem.text = item
    }






}
