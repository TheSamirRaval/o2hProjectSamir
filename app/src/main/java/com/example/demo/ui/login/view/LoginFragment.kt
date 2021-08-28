package com.example.demo.ui.login.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demo.MainActivity
import com.example.demo.R
import com.example.demo.base.BaseFragment
import com.example.demo.base.BaseViewModelFactory
import com.example.demo.databinding.FragmentLoginBinding
import com.example.demo.ui.login.viewmodel.LoginViewModel
import com.example.demo.ui.main.view.MainFragment
import com.example.demo.utils.Utils.Companion.RC_SIGN_IN
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import timber.log.Timber


class LoginFragment : BaseFragment() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val currentUser = auth.currentUser
            updateUI(currentUser)
        }, 1000)

    }

    /**
     * This method is used to initialize views for this screen
     */
    private fun initView() {
        loginViewModel =
            ViewModelProvider(
                this,
                BaseViewModelFactory { LoginViewModel() }).get(LoginViewModel::class.java)
        setGoogleClient()
        setObservers()
        setOnClickListeners()
    }

    private fun setGoogleClient() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        auth = FirebaseAuth.getInstance()

    }


    /**
     * This method is used to set observers
     */
    private fun setObservers() {


        loginViewModel.userValueLogin.observe(viewLifecycleOwner, Observer { userValue ->
            Toast.makeText(context, "User is not available", Toast.LENGTH_SHORT).show()
        })

    }

    /**
     * This method is used to set click listeners for this screen
     */
    private fun setOnClickListeners() {
        /*binding.loginBTN.setOnClickListener {
            loginViewModel.checkUserAvailableOrNot(
                    binding.emailET.text.toString(),
                    binding.passwordET.text.toString()
            )
        }

        binding.registerBTN.setOnClickListener {
            (activity as MainActivity).navigateToFragment(RegisterFragment(), this)
        }*/

        binding.btnGoogleLogin.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!

                Timber.d("firebaseAuthWithGoogle:%s", account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Timber.w(e, "Google sign in failed")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Timber.d("signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Timber.w(task.exception, "signInWithCredential:failure")
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            (activity as MainActivity).navigateToMainFragment(MainFragment(), this)
        }
    }
}
