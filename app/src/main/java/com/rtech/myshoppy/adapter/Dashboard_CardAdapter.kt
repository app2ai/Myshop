package com.rtech.myshoppy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rtech.myshoppy.databinding.ShoppyDashboardCardviewBinding
import com.rtech.myshoppy.model.CardModel

class Dashboard_CardAdapter(private val cardModelList: ArrayList<CardModel>,
                            private val listener: (CardModel, Int) -> Unit
) :
    RecyclerView.Adapter<Dashboard_CardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = ShoppyDashboardCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(cardModelList[position])
       // holder.itemView.setOnClickListener { listener(cardModelList[position], position) }

    }

    override fun getItemCount(): Int {
        return cardModelList.size
    }

    class ViewHolder(var itemBinding: ShoppyDashboardCardviewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(cardModel: CardModel) {
            itemBinding.cardImg.setImageResource(cardModel.image)
            itemBinding.textName.text = cardModel.name
         //  itemBinding.textPrice.text=cardModel.price

        }
    }
}