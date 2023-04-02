package com.rtech.myshoppy.ui.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rtech.myshoppy.databinding.ShoppyCartProductcardBinding
import com.rtech.myshoppy.db.entities.ProductDetailsModel
import com.rtech.myshoppy.ui.dashboard.Dashboard_ProductCardAdapter

class CartProductAdapter(
    var listener: ProductClickDeleteInterface
) :
    RecyclerView.Adapter<CartProductAdapter.ViewHolder>() {
    var mlistener: ProductClickDeleteInterface? = null
    var productDetailList: MutableList<ProductDetailsModel?> = mutableListOf()

    init {
        mlistener = listener
    }

    fun setDataList(mList: List<ProductDetailsModel?>) {
        productDetailList.addAll(mList)
        this.notifyDataSetChanged()
    }
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

            itemBinding.textRemove.setOnClickListener {
                listener.onDeleteIconClick(productModel.id)
            }
        }
    }

    interface ProductClickDeleteInterface {
        fun onDeleteIconClick(productId: Int)
    }
}