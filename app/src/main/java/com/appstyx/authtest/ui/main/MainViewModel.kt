package com.appstyx.authtest.ui.main

import androidx.lifecycle.ViewModel
import com.appstyx.authtest.common.Event

class MainViewModel: ViewModel() {

    enum class Destination {
        Signup, Home
    }

    val changeDestinationEvent = Event<Destination>()

    init {
        changeDestinationEvent.value = Destination.Signup
    }
}