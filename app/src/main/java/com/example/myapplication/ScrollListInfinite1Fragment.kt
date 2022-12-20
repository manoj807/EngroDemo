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


class ScrollListInfinite1Fragment : Fragment() {

    private  lateinit var binding: FragmentScrollListInfinite1Binding
    private lateinit var recyclerView:RecyclerView
    private lateinit var recyclerViewAdapter:RecyclerViewAdapter
    private var isLoading:Boolean=false

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
        initScrollListener()

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
        recyclerViewAdapter = RecyclerViewAdapter(getItemList(0))
        recyclerView.adapter = recyclerViewAdapter
    }

    private fun initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == recyclerViewAdapter.getItemList().size - 1) {
                        //bottom of list!
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        recyclerViewAdapter.getItemList().add("")
        recyclerViewAdapter.notifyItemInserted(recyclerViewAdapter.getItemList().size - 1)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable {
            val list=getItemList(recyclerViewAdapter.getItemList().size - 1)
             recyclerViewAdapter.getItemList().removeAt(recyclerViewAdapter.getItemList().size-1)
            val scrollPosition: Int =  recyclerViewAdapter.getItemList().size
            recyclerViewAdapter.notifyItemRemoved(scrollPosition)

            recyclerViewAdapter.updateItemList(list)

            isLoading = false
        }, 2000)
    }
}