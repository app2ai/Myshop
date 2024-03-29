package com.rtech.myshoppy.ui.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rtech.myshoppy.databinding.FragmentShoppyCartBinding
import com.rtech.myshoppy.databinding.ShoppyCartProductcardBinding
import com.rtech.myshoppy.db.entities.ProductAndCart
import com.rtech.myshoppy.db.entities.ProductDetailsModel
import com.rtech.myshoppy.ui.dashboard.Dashboard_ProductCardAdapter
import com.rtech.myshoppy.ui.dashboard.ShoppyDashboardViewModel
import kotlinx.coroutines.launch

class ShoppyCartFragment : Fragment(), CartProductAdapter.ProductClickDeleteInterface {
    private lateinit var binding: FragmentShoppyCartBinding
    private lateinit var viewModel: ShoppyDashboardViewModel
    private lateinit var adapter: CartProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppyCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShoppyDashboardViewModel::class.java)
        setupRecyclerView()
        viewModel.getCartProductsFromDb(requireContext())
        observeData()
    }

    private fun observeData() {
        viewModel.cartFlow.observe(viewLifecycleOwner) {
            Log.d("CART_TAG", "observeData: \n\n${it}")
            val ll = getCartList(it)
            adapter.setDataList(ll)
        }
    }

    private fun getCartList(ll: List<ProductAndCart>?): MutableList<ProductDetailsModel?> {
        var newList = mutableListOf<ProductDetailsModel?>()
        ll?.forEach {
            if (it.cart != null) newList.add(it.product)
        }
        return newList
    }

    private fun setupRecyclerView() {
        adapter = CartProductAdapter(this@ShoppyCartFragment)
        binding.cartRecycleView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapter
        }
    }

    override fun onDeleteIconClick(productId: Int) {
        viewModel.deleteProductToCart(productId = productId, context = requireContext())
    }
}