package com.example.presentation.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.presentation.fragment.ARG_OBJECT
import com.example.presentation.fragment.ImageContainerFragment

class ImagesCollectionPagerAdapter(
    private val images: List<String>,
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        val imageContainer = ImageContainerFragment()
        imageContainer.arguments = Bundle().apply {
            putString(ARG_OBJECT, images[position])
        }
        return imageContainer
    }

    override fun getCount(): Int = images.size
}