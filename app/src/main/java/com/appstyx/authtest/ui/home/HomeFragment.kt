package com.appstyx.authtest.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import coil.load
import com.appstyx.authtest.data.ApiClient
import com.appstyx.authtest.data.DataRepository
import com.appstyx.authtest.data.model.UserData
import com.appstyx.authtest.databinding.FragmentHomeBinding
import com.appstyx.authtest.ui.main.MainActivity
import com.appstyx.authtest.ui.main.MainViewModel
import com.appstyx.authtest.utils.LoadingStatus
import com.appstyx.authtest.utils.PrefsUtils
import com.appstyx.authtest.utils.hideKeyBoard
import com.appstyx.authtest.utils.showSnackbar
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefsUtils: PrefsUtils

    private val mainActivity: MainActivity
        get() {
            return activity as? MainActivity ?: throw IllegalStateException("Not attached!")
        }

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Factory(DataRepository(ApiClient.apiService), prefsUtils)
    }

    private var snackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefsUtils = PrefsUtils(
            requireContext().getSharedPreferences(
                "global_shared_prefs",
                Context.MODE_PRIVATE
            ), Gson()
        )
        viewModel.getUserData()
        initViewModelObservers()
        initActionsListeners()
    }

    private fun initViewModelObservers() {

        val lifecycleOwner: LifecycleOwner = this
        with(viewModel) {
            loadingStatus.observe(lifecycleOwner) {
                when (it) {
                    is LoadingStatus.Loading -> {
                        showLoading()
                    }

                    is LoadingStatus.Success -> {
                        dismissLoading()
                    }

                    is LoadingStatus.Error -> {
                        showError(it.errorMessage)
                    }
                }
            }

            user.observe(lifecycleOwner) {
                it?.let { setupView(it) }
            }

        }
    }

    private fun setupView(userData: UserData) {
        Log.d("setupView", "setupView: $userData")
        val user = userData.user
        binding.imageViewAvatar.load(user.avatarURL)
        binding.textViewFirstName.text = user.firstName
        binding.textViewLastName.text = user.lastName
    }

    private fun showLoading() {
        snackbar?.dismiss()
        if (isLoading()) return
        requireActivity().hideKeyBoard()
        binding.progressIndicator.show()
    }

    private fun isLoading(): Boolean = binding.progressIndicator.isVisible

    private fun dismissLoading() {
        binding.progressIndicator.hide()
    }

    private fun showError(message: String) {
        requireActivity().hideKeyBoard()
        dismissLoading()
        snackbar?.dismiss()
        snackbar = binding.root.showSnackbar(message, Snackbar.LENGTH_INDEFINITE, "Dismiss") {}
    }
    private fun initActionsListeners() {
        binding.logoutButton.setOnClickListener {
            viewModel.onLogoutClick()
            mainActivity.setDestination(MainViewModel.Destination.Signup)
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}