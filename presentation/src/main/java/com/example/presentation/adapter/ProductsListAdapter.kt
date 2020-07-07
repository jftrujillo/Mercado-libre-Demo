package com.example.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.ProductPreview
import com.example.presentation.databinding.ProductPreviewTemplateBinding

class ProductsListAdapter(
    private val listener: (ProductPreview) -> Unit
) :
    ListAdapter<ProductPreview, RecyclerView.ViewHolder>(ProductPreviewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductPreviewViewHolder(
            ProductPreviewTemplateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val productPreview = getItem(position)
        (holder as ProductPreviewViewHolder).bind(productPreview)
        holder.itemView.setOnClickListener { listener(productPreview) }
    }

    class ProductPreviewViewHolder(
        private val binding: ProductPreviewTemplateBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductPreview) {
            binding.apply {
                productPreview = item
                Glide.with(binding.root)
                    .load(item.thumbnailUrl)
                    .centerCrop()
                    .into(binding.image)

                binding.freeShipping.visibility =
                    if (item.hasFreeShipping) View.VISIBLE else View.INVISIBLE
                executePendingBindings()
            }
        }
    }
}

private class ProductPreviewDiffCallback : DiffUtil.ItemCallback<ProductPreview>() {

    override fun areItemsTheSame(oldItem: ProductPreview, newItem: ProductPreview): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductPreview, newItem: ProductPreview): Boolean {
        return oldItem == newItem
    }
}