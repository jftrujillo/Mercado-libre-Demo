package com.example.presentation.fragment

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.domain.model.ProductDetail
import com.example.domain.util.ErrorType
import com.example.domain.util.Result
import com.example.presentation.R
import com.example.presentation.adapter.ImagesCollectionPagerAdapter
import com.example.presentation.databinding.FragmentProductDetailBinding
import com.example.presentation.util.getErrorMessage
import com.example.presentation.viewmodel.MasterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.error_layout.view.*

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private val args: ProductDetailFragmentArgs by navArgs()
    private val viewModel: MasterDetailViewModel by activityViewModels()

    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var imagesCollectionPagerAdapter: ImagesCollectionPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        listenToProductDetail()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getProductDetail(args.productId)
    }

    private fun listenToProductDetail() {
        viewModel.getDetailedProductListener().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> setLoadingView()
                is Result.Success -> {
                    inflateDetailedView(result.data)
                    setDetailView()
                }
                is Result.Error -> setErrorView(result.exception)
            }
        })
    }

    private fun inflateDetailedView(data: ProductDetail) {
        imagesCollectionPagerAdapter =
            ImagesCollectionPagerAdapter(data.images, childFragmentManager)
        binding.imagesViewPager.adapter = imagesCollectionPagerAdapter

        binding.soldQuantity.text =
            getString(R.string.sold_quantity_label, data.soldQuantity.toString())
        binding.title.text = data.title
        binding.price.text = getString(R.string.price_label, data.price.toString())

        val warrantyString =
            Html.fromHtml(getString(R.string.warranty_label, data.warranty), FROM_HTML_MODE_LEGACY)
        binding.warranty.text = warrantyString

        val tags = data.tags.joinToString(separator = ", ")
        val tagsString = Html.fromHtml(getString(R.string.tags_label, tags), FROM_HTML_MODE_LEGACY)
        binding.tags.text = tagsString

        if (data.description.isNullOrEmpty().not()) {
            val description = Html.fromHtml(
                getString(R.string.description_label, data.description),
                FROM_HTML_MODE_LEGACY
            )
            binding.description.text = description
            binding.description.visibility = View.VISIBLE
        } else {
            binding.description.visibility = View.VISIBLE
        }

        if (data.features.isNullOrEmpty().not()) {
            val features =
                data.features
                    ?.map { it.text }
                    ?.joinToString(separator = "<br><br>")

            val featuresString =
                Html.fromHtml(getString(R.string.features_label, features), FROM_HTML_MODE_LEGACY)
            binding.features.text = featuresString
            binding.features.visibility = View.VISIBLE
        } else binding.features.visibility = View.GONE
    }

    private fun setLoadingView() {
        binding.detailContainer.visibility = View.GONE
        binding.progressContainer.visibility = View.VISIBLE
        binding.errorView.visibility = View.GONE
    }

    private fun setErrorView(exception: ErrorType) {
        binding.detailContainer.visibility = View.GONE
        binding.progressContainer.visibility = View.GONE
        binding.errorView.visibility = View.VISIBLE
        binding.errorView.error_label.text = context?.let { exception.getErrorMessage(it) }
    }

    private fun setDetailView() {
        binding.detailContainer.visibility = View.VISIBLE
        binding.progressContainer.visibility = View.GONE
        binding.errorView.visibility = View.GONE
    }
}