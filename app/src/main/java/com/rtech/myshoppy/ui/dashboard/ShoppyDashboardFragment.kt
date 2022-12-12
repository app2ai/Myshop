package com.rtech.myshoppy.ui.dashboard

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
import com.rtech.myshoppy.databinding.FragmentShoppyDashboardBinding
import com.rtech.myshoppy.ui.login.ShoppyLoginViewModel

class ShoppyDashboardFragment : Fragment() {
    private lateinit var binding: FragmentShoppyDashboardBinding
    private lateinit var viewModel: ShoppyLoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentShoppyDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShoppyLoginViewModel::class.java)

        binding.btnGoToLogin.setOnClickListener {
            findNavController().navigate(R.id.shoppyLoginFragment, Bundle().apply {
                this.putString("title", "Login Page View")
            })
        }
        binding.btnLogout.setOnClickListener {
            viewModel.logoutUser(requireContext())
            val sp = CachePref.getCacheInstance(requireContext())
            val editor = CachePref.getEditor(sp)
            CachePref.logoutUserCache(editor)
        }
        observeData()
    }

    private fun observeData() {
        viewModel.userLogoutLiveData.observe(viewLifecycleOwner) {
            if (it){
                Toast.makeText(context, "User logout successfully", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Oops, Logout failed", Toast.LENGTH_LONG).show()
            }
        }
    }
}