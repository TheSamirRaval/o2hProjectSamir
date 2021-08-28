package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.demo.base.navigation.FragmentNavigation
import com.example.demo.databinding.ActivityMainBinding
import com.example.demo.ui.login.view.LoginFragment
import com.example.demo.ui.main.view.MainFragment

class MainActivity : AppCompatActivity(), FragmentNavigation {

    lateinit var binding: ActivityMainBinding

    var LoginFragment: LoginFragment?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        initView()
    }

    //This Function is used to initialization.
    private fun initView() {
        LoginFragment= LoginFragment()
        setInitialFragment()
    }

    private fun setInitialFragment() {
        setFragmentAsRoot(R.id.container_main,LoginFragment!!)
    }

    //This Function is used to navigate the Fragment
    fun navigateToFragment(fragment: Fragment, frag: Fragment? = null, requestCode: Int? = null) {
        addNew(R.id.container_main,fragment, transition = true, targetFragment = frag, requestCode = requestCode)
    }

    fun navigateToMainFragment(fragment: Fragment, frag: Fragment? = null, requestCode: Int? = null) {
        setFragmentAsRoot(R.id.container_main,fragment)
    }
}