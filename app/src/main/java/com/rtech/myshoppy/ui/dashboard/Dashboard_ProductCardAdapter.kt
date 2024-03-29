package com.rtech.myshoppy.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rtech.myshoppy.databinding.ShoppyDashboardProductcardBinding
import com.rtech.myshoppy.db.entities.ProductDetailsModel

class Dashboard_ProductCardAdapter(
    var productDetailList: List<ProductDetailsModel>,
    var listener: ProductClickListener
) : RecyclerView.Adapter<Dashboard_ProductCardAdapter.ViewHolder>() {

    var mlistener: ProductClickListener? = null
    init {
        mlistener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = ShoppyDashboardProductcardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(productDetailList[position])
    }

    override fun getItemCount(): Int {
        return productDetailList.size
    }

    inner class ViewHolder(var itemBinding: ShoppyDashboardProductcardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindItem(productModel: ProductDetailsModel) {
            itemBinding.textName.text = productModel.productName
            itemBinding.productRating.rating = (productModel.rating).toFloat()
            itemBinding.textPrice.text = productModel.productDesc
            itemBinding.root.setOnClickListener {
                listener?.onProductClick(productModel.id)
            }
        }
    }

    interface ProductClickListener {
        fun onProductClick(productId: Int)
    }
}