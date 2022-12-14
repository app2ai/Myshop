package com.rtech.myshoppy.ui.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.rtech.myshoppy.databinding.FragmentShoppyRegisterBinding
import com.rtech.myshoppy.db.entities.UserDetailsModel
import com.rtech.myshoppy.utils.clear

class ShoppyRegisterFragment : Fragment(), TextWatcher {
    private lateinit var binding: FragmentShoppyRegisterBinding
    private lateinit var viewModel: ShoppyRegisterViewModel

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
        binding.etPassword.addTextChangedListener(this)
        binding.etConfPassword.addTextChangedListener(this)
        binding.btnRegister.setOnClickListener {
            context?.let { it1 ->
                viewModel.addUser(
                    UserDetailsModel(
                        uname = binding.etUserName.text.toString(),
                        age = binding.etAge.text.toString(),
                        mobile = binding.etContact.text.toString(),
                        password = binding.etPassword.text.toString()
                    ),
                    it1
                )
            }
        }
    }

    private fun observeData() {
        viewModel.unameLiveData.observe(viewLifecycleOwner) {
            if (!it) binding.etUserName.error = "Username is short"
        }
        viewModel.enableLiveData.observe(viewLifecycleOwner) {
            binding.btnRegister.isEnabled = it
        }
        viewModel.mobileLiveData.observe(viewLifecycleOwner) {
            if (!it) binding.etContact.error = "Invalid mobile number"
        }
        viewModel.confPasswordLiveData.observe(viewLifecycleOwner) {
            if (!it)
                binding.etConfPassword.error = "Password mismatch"
        }
        viewModel.savingLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(context, "User registration ID: $it", Toast.LENGTH_LONG).show()
            clearData()
        }
    }

    private fun clearData() {
        binding.etUserName.apply {
            clear()
            requestFocus()
        }
        binding.etAge.clear()
        binding.etContact.clear()
        binding.etPassword.clear()
        binding.etConfPassword.clear()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
    override fun afterTextChanged(p0: Editable?) { }
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (binding.etUserName.hasFocus()) {
            viewModel.checkName(p0.toString())
        } else if (binding.etContact.hasFocus()) {
            viewModel.checkMobileNumber(p0.toString())
        } else if(binding.etPassword.hasFocus()){
            viewModel.checkPassword(p0.toString())
        } else if(binding.etConfPassword.hasFocus()){
            viewModel.checkConfPassword(p0.toString())
        }
    }
}