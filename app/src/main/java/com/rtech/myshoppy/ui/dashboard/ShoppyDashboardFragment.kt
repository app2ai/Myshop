package com.rtech.myshoppy.ui.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rtech.myshoppy.R
import com.rtech.myshoppy.databinding.FragmentShoppyDashboardBinding
import com.rtech.myshoppy.db.entities.ProductDetailsModel
import com.rtech.myshoppy.ui.login.ShoppyLoginViewModel

class ShoppyDashboardFragment : Fragment(), Dashboard_ProductCardAdapter.ProductClickListener {
    private lateinit var binding: FragmentShoppyDashboardBinding
    private lateinit var viewModel: ShoppyDashboardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentShoppyDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShoppyDashboardViewModel::class.java)
        viewModel.getAllProductsFromDb(requireContext())

        observeData()
    }

    private fun observeData() {
        viewModel.productsLiveData.observe(viewLifecycleOwner) {
            setupRecyclerView(it)
        }
    }

    private fun setupRecyclerView(list: List<ProductDetailsModel>) {
        binding.dashboardRecyclerView.apply {
            layoutManager= GridLayoutManager(context,2)
            adapter = Dashboard_ProductCardAdapter(list, this@ShoppyDashboardFragment)
        }
    }

    override fun onProductClick(productId: Int) {
        findNavController().navigate(
            R.id.shoppyProductDetailsFragment,
            bundleOf("ID" to productId)
        )
    }
}
