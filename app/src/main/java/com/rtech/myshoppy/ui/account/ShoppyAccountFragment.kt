package com.rtech.myshoppy.ui.account

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
import com.rtech.myshoppy.databinding.FragmentAccountBinding
import com.rtech.myshoppy.db.entities.UserDetailsModel
import com.rtech.myshoppy.ui.login.ShoppyLoginViewModel

class ShoppyAccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var viewModel: ShoppyLoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShoppyLoginViewModel::class.java)

        binding.btnLogout.setOnClickListener {
            viewModel.logoutUser(requireContext())
            val sp = CachePref.getCacheInstance(requireContext())
            val editor = CachePref.getEditor(sp)
            CachePref.logoutUserCache(editor)
        }

        binding.txtLoginText.setOnClickListener {
            findNavController().navigate(R.id.shoppyLoginFragment, Bundle().apply {
                this.putString("title", "Login Page View")
            })
        }
        checkIfUserSessionIsOverOrNot()
        observeData()
    }

    private fun observeData() {
        viewModel.userLogoutLiveData.observe(viewLifecycleOwner) {
            if (it){
                Toast.makeText(context, "User logout successfully", Toast.LENGTH_LONG).show()
                checkIfUserSessionIsOverOrNot()
            } else {
                Toast.makeText(context, "Oops, Logout failed", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.userNameLiveData.observe(viewLifecycleOwner){
            binding.textName.text = "Welcome, $it"
        }
    }

    private fun checkIfUserSessionIsOverOrNot() {
        val sp = CachePref.getCacheInstance(requireContext())
        val status = CachePref.getUserIdFromCache(sp)
        if (status > 0) {
            binding.txtLoginText.apply {
                visibility = View.VISIBLE
                // name display
                viewModel.getUserDetails(status,requireContext())
            }
        } else {
            binding.txtLoginText.apply {
                visibility = View.VISIBLE
            }
        }
    }
}