package com.appstyx.authtest.ui.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.appstyx.authtest.data.DataRepository
import kotlinx.coroutines.launch

class SignupViewModel(private val dataRepository: DataRepository): ViewModel() {

    fun onSignupClick() {

    }

    fun getGenders() {
        viewModelScope.launch {
            val data = dataRepository.getGenders()
            Log.d("launchman", ": $data")
        }
    }

    class Factory(private val dataRepository: DataRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SignupViewModel(dataRepository) as T
        }
    }
}