package com.example.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ProductPreview
import com.example.domain.util.ErrorType
import com.example.domain.util.Result
import com.example.presentation.adapter.ProductsListAdapter
import com.example.presentation.databinding.FragmentProductListBinding
import com.example.presentation.util.getErrorMessage
import com.example.presentation.viewmodel.MasterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.error_layout.view.*

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private val viewModel: MasterDetailViewModel by activityViewModels()

    private lateinit var productListAdapter: ProductsListAdapter

    private lateinit var binding: FragmentProductListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productListAdapter = ProductsListAdapter { navigateToDetailScreen(it) }
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        binding.list.apply {
            adapter = productListAdapter
            addItemDecoration(DividerItemDecoration(this.context, RecyclerView.VERTICAL))
        }
        listenProductList()
        return binding.root
    }

    private fun navigateToDetailScreen(productPreview: ProductPreview) {
        val action =
            ProductListFragmentDirections.actionProductListToProductDetail(productPreview.id)
        findNavController().navigate(action)
    }

    private fun listenProductList() {
        viewModel.getProductsListener().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> setLoadingView()
                is Result.Success -> {
                    if (result.data.isNotEmpty()) {
                        productListAdapter.submitList(result.data)
                        setProductListView()
                    } else {
                        setNoProductListView()
                    }
                }
                is Result.Error -> setErrorView(result.exception)
            }
        })
    }

    private fun setNoProductListView() {
        binding.noPreviewContainer.visibility = View.GONE
        binding.progressContainer.visibility = View.GONE
        binding.list.visibility = View.GONE
        binding.errorView.visibility = View.GONE
        binding.noResultContainer.visibility = View.VISIBLE
    }

    private fun setProductListView() {
        binding.noPreviewContainer.visibility = View.GONE
        binding.progressContainer.visibility = View.GONE
        binding.list.visibility = View.VISIBLE
        binding.errorView.visibility = View.GONE
        binding.noResultContainer.visibility = View.GONE
    }

    private fun setLoadingView() {
        binding.noPreviewContainer.visibility = View.GONE
        binding.progressContainer.visibility = View.VISIBLE
        binding.list.visibility = View.GONE
        binding.errorView.visibility = View.GONE
        binding.noResultContainer.visibility = View.GONE
    }

    private fun setErrorView(exception: ErrorType) {
        binding.noPreviewContainer.visibility = View.GONE
        binding.progressContainer.visibility = View.GONE
        binding.list.visibility = View.GONE
        binding.errorView.visibility = View.VISIBLE
        binding.noResultContainer.visibility = View.GONE
        binding.errorView.error_label.text = context?.let { exception.getErrorMessage(it) }
    }
}