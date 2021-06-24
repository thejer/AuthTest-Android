package com.appstyx.authtest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.appstyx.authtest.ui.main.MainViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    private val changeDestinationObserver: Observer<MainViewModel.Destination> = mock()

    @Before
    fun setup() {
        viewModel = MainViewModel().apply {
            changeDestinationEvent.observeForever(changeDestinationObserver)
        }
    }

    @Test
    fun `always redirect to Signup on init now that navigation to Home is not implemented yet`() {
        verify(changeDestinationObserver, times(1)).onChanged(MainViewModel.Destination.Signup)
    }
}