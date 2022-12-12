package com.rtech.myshoppy.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rtech.myshoppy.R
import com.rtech.myshoppy.cache.CachePref
import com.rtech.myshoppy.databinding.FragmentShoppyLoginBinding

class ShoppyLoginFragment : Fragment() {
    private lateinit var binding: FragmentShoppyLoginBinding
    private lateinit var viewModel: ShoppyLoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentShoppyLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShoppyLoginViewModel::class.java)

        binding.txtSignUp.setOnClickListener{
            findNavController().navigate(
                R.id.shoppyRegisterFragment
            )
        }

        binding.btnLogin.setOnClickListener {
            if (binding.editUserName.text.isBlank() or binding.editPassword.text.isBlank()) {
                Toast.makeText(context, "Please, enter mobile and password", Toast.LENGTH_LONG).show()
            } else {
                viewModel.loginUserFromDb(
                    binding.editUserName.text.toString(),
                    binding.editPassword.text.toString(),
                    requireContext()
                )
            }
        }

        observeData()
    }

    private fun observeData() {
        viewModel.userLiveData.observe(viewLifecycleOwner) {
            when(it){
                LoginFailed -> {
                    Toast.makeText(context, "Login failed: User and Pass not matched", Toast.LENGTH_LONG).show()
                }
                is LoginSuccess -> {
                    Toast.makeText(context, "Hi ${it.obj.uname}, Login successful", Toast.LENGTH_LONG).show()
                    // Save login flag to true once user logged in successfully
                    it.obj.isLogin = true
                    viewModel.updateLoginStatus(it.obj, requireContext())
                    val sp = CachePref.getCacheInstance(requireContext())
                    val editor = CachePref.getEditor(sp)
                    CachePref.setUserIdToCache(editor, it.obj.id)
                }
            }
        }
    }
}