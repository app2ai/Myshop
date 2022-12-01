package com.rtech.myshoppy.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rtech.myshoppy.R
import com.rtech.myshoppy.adapter.Dashboard_CardAdapter
import com.rtech.myshoppy.databinding.FragmentShoppyDashboardBinding
import com.rtech.myshoppy.model.CardModel

class ShoppyDashboardFragment : Fragment() {
    private  lateinit var binding: FragmentShoppyDashboardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentShoppyDashboardBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root

       }

    private fun setupRecyclerView() {
        binding.dashboardRecyclerView.apply {
            layoutManager= GridLayoutManager(context,2)

            adapter = Dashboard_CardAdapter(createCardModel()) { cardModel, position ->
                }
        }

    }

    private fun createCardModel(): ArrayList<CardModel> {
        return arrayListOf<CardModel>(
            CardModel(R.drawable.apple_watch," Apple Watch"),
            CardModel(R.drawable.apple_watch,"Apple Watch Series 5 "),
            CardModel(R.drawable.apple_watch,"Apple Watch Series 6"),
            CardModel(R.drawable.apple_watch,"Apple Watch Series 6"),
            CardModel(R.drawable.apple_watch,"Apple Watch Series 6")

        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnGoToLogin).setOnClickListener {
            findNavController().navigate(R.id.shoppyLoginFragment, Bundle().apply {
                this.putString("title", "Login Page View")
            })
        }
    }
}