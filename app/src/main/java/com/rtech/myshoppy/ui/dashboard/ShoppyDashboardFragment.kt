package com.rtech.myshoppy.ui.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rtech.myshoppy.databinding.FragmentShoppyDashboardBinding
import com.rtech.myshoppy.db.entities.ProductDetailsModel
import com.rtech.myshoppy.ui.login.ShoppyLoginViewModel

class ShoppyDashboardFragment : Fragment() {
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
            adapter = Dashboard_ProductCardAdapter(list, context)
        }
    }
}
