package com.example.newsapp.ui.component.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.R
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.databinding.FragmentLoginBinding
import com.example.newsapp.ui.component.profile.ProfileFragment
import com.example.newsapp.ui.component.register.RegisterFragment
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider


class LoginFragment : BaseFragment() {

    companion object{
        private const val RC_SIGN_IN=120
    }

    private lateinit var googleSignInClient:GoogleSignInClient
    lateinit var binding:FragmentLoginBinding
    lateinit var callbackManager: CallbackManager
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.clicentID))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this.requireActivity(),gso)

        FacebookSdk.sdkInitialize(this.requireContext());
        callbackManager = CallbackManager.Factory.create()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        facebookLoginControl()
    }


    private fun initView(){
        viewModel=ViewModelProvider(this)[LoginViewModel::class.java]
        binding.btnLogin.setOnClickListener {
            viewModel.login(binding.txtEmail.text.toString().trim(),binding.txtPass.text.toString())
            signEvent()
        }
        binding.txtRegister.setOnClickListener {
           connectFragment(RegisterFragment())
        }

        binding.btnGoogle.setOnClickListener {
            signIn()
        }
    }
    private fun signEvent(){
        viewModel.loginStatus.observe(this,{
            when (it) {
                1 -> {
                    Toast.makeText(context, getString(R.string.emptyArea), Toast.LENGTH_SHORT).show()
                }
                2 -> {
                    viewModel.failMessage.observe(this,{ failMessage->
                        if (failMessage!=""){
                            Toast.makeText(context,failMessage, Toast.LENGTH_SHORT).show()
                            viewModel.failMessage.value=""
                        }
                    })
                }
                3 -> {
                    connectFragment(ProfileFragment())
                }
            }
        })
        viewModel.loginStatus.value=0
    }
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                account.idToken?.let {
                    viewModel.firebaseAuthWithGoogle(it,this.requireActivity())
                    viewModel.googleLoginStatus.observe(this,{
                        if (it){
                            connectFragment(ProfileFragment())
                        }else{
                            Toast.makeText(context, getString(R.string.notFoundAcc), Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun facebookLoginControl(){

        binding.loginButton.setReadPermissions("email", "public_profile")
        binding.loginButton.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d(TAG, "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d(TAG, "facebook:onCancel")
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "facebook:onError", error)
            }
        })
    }
    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this.requireActivity()) { task->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user!=null){
            Toast.makeText(context, user.displayName.toString()+getString(R.string.succes), Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, getString(R.string.notConnectionFacebook), Toast.LENGTH_SHORT).show()

        }
    }

}