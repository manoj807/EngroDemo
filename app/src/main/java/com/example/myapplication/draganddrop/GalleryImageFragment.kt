package com.example.myapplication.draganddrop

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentGalleryImageBinding
import com.example.myapplication.databinding.FragmentScrollListInfinite1Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryImageFragment : Fragment() {

    private  lateinit var binding: FragmentGalleryImageBinding
    private lateinit var recyclerView:RecyclerView
    private val imageViewModel:ImageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentGalleryImageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          imageViewModel.getAllImages()
        recyclerView=binding.recyclerView

        observer()


    }

    fun  observer()
    {
        imageViewModel.getImageList().observe(viewLifecycleOwner) { imageList->
            imageList?.takeIf { it.isNotEmpty()}?.let {
                initAdapter(it)
            }

        }

    }







    private fun initAdapter(list:List<String>) {
        val recyclerViewAdapter = GalleryImagesAdapter(list)
        recyclerView.adapter = recyclerViewAdapter
    }



}