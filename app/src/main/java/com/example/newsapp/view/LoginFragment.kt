package com.example.newsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    lateinit var binding:FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth
        binding= FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewControl(view)
    }

    private fun viewControl(view: View){
        binding.btnLogin.setOnClickListener {
            login(view)
        }
        binding.txtRegister.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.loginTOregister)
        }
    }
    private fun login(view: View){
        var email=binding.txtEmail.text.toString().trim()
        var pass=binding.txtPass.text.toString()
        if (email==""||pass==""){
            Toast.makeText(context, "Alanlar tam doldurulmalıdır!", Toast.LENGTH_SHORT).show()
        }else{
            auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener { task->
                if (task.isSuccessful){
                    Navigation.findNavController(view).navigate(R.id.loginTOprofile)
                }
            }.addOnFailureListener {
                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

}