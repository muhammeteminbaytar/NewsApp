package com.example.newsapp.ui.component.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.R
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.databinding.FragmentProfileBinding
import com.example.newsapp.ui.component.login.LoginFragment
import com.example.newsapp.ui.component.register.RegisterViewModel


class ProfileFragment : BaseFragment() {

    lateinit var binding:FragmentProfileBinding
    private lateinit var viewModel:ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentProfileBinding.inflate(inflater,container,false)
            return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        viewModel= ViewModelProvider(this)[ProfileViewModel::class.java]

        if(auth.currentUser==null){
            connectFragment(LoginFragment())
        }else{
            binding.txtEmail.text=auth.currentUser?.email
            if(auth.currentUser?.displayName.equals(null)||auth.currentUser?.displayName.equals("")){
                viewModel.getFirestoreData()
                viewModel.name.observe(this,{
                    binding.txtName.text=it
                })
            }else{
                binding.txtName.text=auth.currentUser?.displayName
            }
        }
        binding.btnLogout.setOnClickListener {
            viewModel.accSignOut()
            connectFragment(LoginFragment())
        }
    }


}