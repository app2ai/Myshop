package com.rtech.myshoppy.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rtech.myshoppy.databinding.ShoppyDashboardProductcardBinding
import com.rtech.myshoppy.db.entities.ProductDetailsModel

class Dashboard_ProductCardAdapter( var productDetailList: List<ProductDetailsModel>,
                                   private val context: Context) :
    RecyclerView.Adapter<Dashboard_ProductCardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = ShoppyDashboardProductcardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(productDetailList[position])

    }

    override fun getItemCount(): Int {
        return productDetailList.size
    }

    class ViewHolder(var itemBinding: ShoppyDashboardProductcardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(productModel: ProductDetailsModel) {
            itemBinding.textName.text = productModel.productName
            itemBinding.cardImg.setImageResource(productModel.id)
           itemBinding.textPrice.text=productModel.productDesc

        }
    }
}