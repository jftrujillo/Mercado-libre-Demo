package com.example.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.util.Result
import com.example.presentation.adapter.ProductsListAdapter
import com.example.presentation.databinding.FragmentProductListBinding
import com.example.presentation.viewmodel.MasterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

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
        // Inflate the layout for this fragment
        productListAdapter = ProductsListAdapter()
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        binding.list.apply {
            adapter = productListAdapter
            addItemDecoration(DividerItemDecoration(this.context, RecyclerView.VERTICAL))
        }
        listenProductList()
        viewModel.searchBy("play station")

        return binding.root
    }

    private fun listenProductList() {
        viewModel.getProducts().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> setLoadingView()
                is Result.Success -> {
                    productListAdapter.submitList(result.data)
                    setProductListView()
                }
                is Result.Error -> setErrorView()
            }
        })
    }

    private fun setProductListView() {
        binding.progressDialogContainer.visibility = View.GONE
        binding.list.visibility = View.VISIBLE
        binding.errorView.visibility = View.GONE
    }

    private fun setLoadingView() {
        binding.progressDialogContainer.visibility = View.VISIBLE
        binding.list.visibility = View.GONE
        binding.errorView.visibility = View.GONE
    }

    private fun setErrorView() {
        binding.progressDialogContainer.visibility = View.GONE
        binding.list.visibility = View.GONE
        binding.errorView.visibility = View.VISIBLE
    }
}