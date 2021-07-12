package com.appstyx.authtest.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.appstyx.authtest.data.ApiClient
import com.appstyx.authtest.data.DataRepository
import com.appstyx.authtest.databinding.FragmentSignupBinding

class SignupFragment: Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignupViewModel by viewModels {
        SignupViewModel.Factory(DataRepository(ApiClient.apiService))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModelObservers()
        initActionsListeners()
    }

    private fun initViewModelObservers() {
        // TODO
    }

    private fun initActionsListeners() {
        binding.signupButton.setOnClickListener {
            viewModel.onSignupClick()
        }
    }

    companion object {
        fun newInstance() = SignupFragment()
    }

}