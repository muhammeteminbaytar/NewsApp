package com.example.newsapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment() {

    lateinit var binding:FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        auth = Firebase.auth
        binding= FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewControl()
    }
    private fun viewControl(){
         binding.btnRegister.setOnClickListener {
             var email =binding.txtEmail.text.toString().trim()
             var pass=binding.txtPass.text.toString()
             var name=binding.txtName.text.toString()
             if (email==""||pass==""||name==""){
                 Toast.makeText(context, "Alanlar Boş Bırakılamaz!", Toast.LENGTH_SHORT).show()
             }else{
                register(email,pass,name,it)
             }
         }
    }

    private fun register(email:String, password:String,name:String,view:View){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this.requireActivity()){ task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Kayıt Başarılı", Toast.LENGTH_SHORT).show()
                    hashMapCreater(name)
                    Navigation.findNavController(view).navigate(R.id.registerTOprofil)
                }
            }.addOnFailureListener {
                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()
                Toast.makeText(context, email, Toast.LENGTH_SHORT).show()

            }
    }
    private fun hashMapCreater(name:String){
        val userMap= hashMapOf<String,String>()
        userMap["name"] = name
        auth.currentUser?.let { userMap.put("userID", it.uid) }
        db.collection("users").add(userMap)
            .addOnSuccessListener {

            }.addOnFailureListener {
                println(it.localizedMessage)
            }
    }

}