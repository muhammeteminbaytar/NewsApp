package com.example.newsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.protobuf.Value


class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    lateinit var binding:FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth
        binding= FragmentProfileBinding.inflate(inflater,container,false)
            return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewControl()
    }

    private fun viewControl(){
        binding.txtEmail.text=auth.currentUser?.email
        getFirestoreData()
        binding.btnLogout.setOnClickListener {
            auth.signOut()
            Navigation.findNavController(it).navigate(R.id.profilTOlogin)
        }
    }
    private fun getFirestoreData(){

        db.collection("users").whereEqualTo("userID",auth.currentUser?.uid)
            .addSnapshotListener { value, error ->
               var name= value?.documents?.get(0)?.get("name").toString()
                binding.txtName.text=name
            }
    }



}