package com.appstyx.authtest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appstyx.authtest.common.Event
import com.appstyx.authtest.utils.PrefsUtils

class MainViewModel( prefsUtils: PrefsUtils): ViewModel() {

    enum class Destination {
        Signup, Home
    }

    val changeDestinationEvent = Event<Destination>()

    init {
        changeDestinationEvent.value = if (prefsUtils.isLoggedIn) Destination.Home else Destination.Signup
    }

    class Factory(private val prefsUtils: PrefsUtils) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(prefsUtils) as T
        }
    }
}