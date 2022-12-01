package com.rtech.myshoppy.ui.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.rtech.myshoppy.databinding.FragmentShoppyRegisterBinding

class ShoppyRegisterFragment : Fragment(), TextWatcher {
    private lateinit var binding: FragmentShoppyRegisterBinding
    private lateinit var viewModel: ShoppyRegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentShoppyRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShoppyRegisterViewModel::class.java)
        initialiseView()
        observeData()
    }

    private fun initialiseView() {
        binding.etUserName.addTextChangedListener(this)
        binding.etContact.addTextChangedListener(this)
    }

    private fun observeData() {
        viewModel.unameLiveData.observe(viewLifecycleOwner) {
            if (!it) {
                binding.etUserName.error = "Username is short"
            }
        }
        viewModel.enableLiveData.observe(viewLifecycleOwner) {
            binding.btnRegister.isEnabled = it
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
    override fun afterTextChanged(p0: Editable?) { }
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (binding.etUserName.isFocusable) {
            viewModel.checkName(p0.toString())
        } else if (binding.etContact.isFocusable) {
            viewModel.checkMobileNumber(p0.toString())
        }
    }

}