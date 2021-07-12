package com.appstyx.authtest.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.appstyx.authtest.R
import com.appstyx.authtest.ui.home.HomeFragment
import com.appstyx.authtest.ui.main.MainViewModel.Destination.Home
import com.appstyx.authtest.ui.main.MainViewModel.Destination.Signup
import com.appstyx.authtest.ui.signup.SignupFragment
import com.appstyx.authtest.utils.PrefsUtils
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var prefsUtils: PrefsUtils

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.Factory(prefsUtils)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefsUtils = PrefsUtils(
            this.getSharedPreferences(
                "global_shared_prefs",
                MODE_PRIVATE
            ), Gson()
        )

        initViewModelObservers()
    }

    fun setDestination(destination: MainViewModel.Destination) {
        viewModel.changeDestinationEvent.value = destination
    }

    private fun initViewModelObservers() {
        val lifecycleOwner: LifecycleOwner = this
        with(viewModel) {
            changeDestinationEvent.observe(lifecycleOwner) { destination ->
                val fragment = when (destination) {
                    Signup -> SignupFragment.newInstance()
                    Home -> HomeFragment.newInstance()
                    else -> throw IllegalArgumentException("Destination not supported")
                }
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
            }
        }
    }
}