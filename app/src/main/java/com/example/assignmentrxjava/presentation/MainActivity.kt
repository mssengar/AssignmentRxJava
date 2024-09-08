package com.example.assignmentrxjava.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.text.layoutDirection
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.assignmentrxjava.R
import com.example.assignmentrxjava.utils.PrefHelper
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PrefHelper.init(this)
        var isLogin = PrefHelper.isLogin

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


        if (isLogin) {
//            val navOptions = NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()
            navController.navigate(R.id.action_loginFragment_to_postListFragment, navOptions = null, args = null )

        }

        window.decorView.layoutDirection = Locale.getDefault().layoutDirection

//       checkBackStack(navHostFragment)
    }

    private fun checkBackStack(navHostFragment: NavHostFragment) {
        onBackPressedDispatcher.addCallback(this /* lifecycle owner */, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toast.makeText(applicationContext, "count ${navHostFragment.childFragmentManager.backStackEntryCount}", Toast.LENGTH_LONG).show()
            }
        })
    }

}