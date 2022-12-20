package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentScrollListInfinite1Binding


class ScrollListInfinite2Fragment : Fragment() {

    private  lateinit var binding: FragmentScrollListInfinite1Binding
    private lateinit var recyclerView:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentScrollListInfinite1Binding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView=binding.recyclerView

        initAdapter();


    }


    fun getItemList(startIndex:Int):MutableList<String>
    {
        var list = mutableListOf<String>()
        val endIndex=startIndex+49

        for (i in startIndex..endIndex)
        {
            list.add("news item".plus(i+1))


        }


        return list


    }




    private fun initAdapter() {
        val recyclerViewAdapter = RecyclerViewCircularAdapter(getItemList(0))
        recyclerView.adapter = recyclerViewAdapter
    }



}