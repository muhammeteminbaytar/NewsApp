package com.example.newsapp.ui.base

import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.data.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth

abstract class BaseFragment:Fragment() {

    open var useSharedViewModel: Boolean = false

    protected val auth: FirebaseAuth=FirebaseRepository().getFirebaseAuth()
    protected val db = FirebaseRepository().getFirebaseFirestore()

    protected fun connectFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment).commit()
    }
}