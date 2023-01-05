package com.rtech.myshoppy.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.rtech.myshoppy.databinding.FragmentShoppyProductDetailsBinding
import com.rtech.myshoppy.ui.dashboard.ShoppyDashboardViewModel

class ShoppyProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentShoppyProductDetailsBinding
    private lateinit var viewModel: ShoppyDashboardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentShoppyProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        print("Product ID is: ${arguments?.getInt("ID")}")
        viewModel= ViewModelProvider(this).get(ShoppyDashboardViewModel::class.java)
        observeData()
    }

    private fun observeData() {
        viewModel.productsLiveData.observe(viewLifecycleOwner){

        }
    }
}