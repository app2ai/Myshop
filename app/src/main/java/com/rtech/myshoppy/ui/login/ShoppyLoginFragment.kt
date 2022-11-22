package com.rtech.myshoppy.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.rtech.myshoppy.R
import com.rtech.myshoppy.databinding.FragmentShoppyLoginBinding

class ShoppyLoginFragment : Fragment() {
    private lateinit var binding: FragmentShoppyLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentShoppyLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtSignUp.setOnClickListener{
            findNavController().navigate(
                R.id.shoppyRegisterFragment
            )
        }
    }
}