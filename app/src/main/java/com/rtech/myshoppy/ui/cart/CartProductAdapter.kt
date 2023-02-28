package com.rtech.myshoppy.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rtech.myshoppy.databinding.ShoppyCartProductcardBinding
import com.rtech.myshoppy.db.entities.ProductDetailsModel

class CartProductAdapter(var productDetailList: List<ProductDetailsModel?>) :
    RecyclerView.Adapter<CartProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            ShoppyCartProductcardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(productDetailList[position]!!)
    }

    override fun getItemCount(): Int {
        return productDetailList!!.size
    }

    inner class ViewHolder(var itemBinding: ShoppyCartProductcardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(productModel: ProductDetailsModel) {
            itemBinding.textName.text = productModel.productName
            itemBinding.ratingProduct.rating = (productModel.rating).toFloat()
            itemBinding.textSellPrice.text = productModel.sellingPrice.toString()
            itemBinding.textDiscount.text = productModel.discount.toString()
            itemBinding.textDiscription.text = productModel.productDesc
        }
    }
}