package com.example.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.presentation.adapter.ImagesCollectionPagerAdapter
import com.example.presentation.databinding.ImageContainerFragmentBinding

const val ARG_OBJECT = "img_url"

class ImageContainerFragment : Fragment() {

    private lateinit var binding: ImageContainerFragmentBinding
    private lateinit var imagesCollectionPagerAdapter: ImagesCollectionPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ImageContainerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            val imageUrl = getString(ARG_OBJECT)
            context?.let {
                Glide
                    .with(it)
                    .load(imageUrl)
                    .centerCrop()
                    .into(binding.imageContainer)
            }
        }
    }
}