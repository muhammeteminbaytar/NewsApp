package com.example.newsapp.ui.component.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentRegisterBinding
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.ui.component.profile.ProfileFragment


class RegisterFragment : BaseFragment() {

    private val viewModel : RegisterViewModel by viewModels()

    lateinit var binding:FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding= FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView(){
         binding.btnRegister.setOnClickListener {
             val email =binding.txtEmail.text.toString().trim()
             val pass=binding.txtPass.text.toString()
             val name=binding.txtName.text.toString()
             if (email==""||pass==""||name==""){
                 Toast.makeText(context, R.string.emptyArea, Toast.LENGTH_SHORT).show()
             }else{
                 viewModel.register(auth,email,pass,name,db)
                 viewModel.isSuccess.observe(this,{
                     if(it){
                         connectFragment(ProfileFragment())
                     }
                 })
             }
         }
    }

}