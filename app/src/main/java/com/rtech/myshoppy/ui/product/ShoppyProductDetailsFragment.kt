package com.rtech.myshoppy.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rtech.myshoppy.R
import com.rtech.myshoppy.databinding.FragmentShoppyProductDetailsBinding

class ShoppyProductDetailsFragment : Fragment() {
    private  lateinit var binding: FragmentShoppyProductDetailsBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentShoppyProductDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }


}