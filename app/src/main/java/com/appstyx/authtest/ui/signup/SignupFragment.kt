package com.appstyx.authtest.ui.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.appstyx.authtest.R
import com.appstyx.authtest.data.ApiClient
import com.appstyx.authtest.data.DataRepository
import com.appstyx.authtest.data.model.Gender
import com.appstyx.authtest.data.model.KeyValue
import com.appstyx.authtest.data.model.KeyValues
import com.appstyx.authtest.data.model.SignupRequest
import com.appstyx.authtest.databinding.FragmentSignupBinding
import com.appstyx.authtest.ui.main.MainActivity
import com.appstyx.authtest.ui.main.MainViewModel
import com.appstyx.authtest.utils.*
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

class SignupFragment : Fragment() {

    private val mainActivity: MainActivity
        get() {
            return activity as? MainActivity ?: throw IllegalStateException("Not attached!")
        }

    private lateinit var prefsUtils: PrefsUtils

    private val viewModel: SignupViewModel by viewModels {
        SignupViewModel.Factory(DataRepository(ApiClient.apiService), prefsUtils)
    }
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private var snackbar: Snackbar? = null

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
        prefsUtils = PrefsUtils(
            requireContext().getSharedPreferences(
                "global_shared_prefs",
                Context.MODE_PRIVATE
            ), Gson()
        )

        viewModel.getGenders()
        initViewModelObservers(viewModel)
        initActionsListeners(viewModel)
    }

    private fun initViewModelObservers(viewModel: SignupViewModel) {
        val lifecycleOwner: LifecycleOwner = this
        with(viewModel) {
            genders.observe(lifecycleOwner) {
                it?.let {
                    setupGendersSpinner(it.genders)
                }
            }

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

            token.observe(lifecycleOwner) {
                it?.let {
                    mainActivity.setDestination(MainViewModel.Destination.Home)
                }
            }
        }
    }

    private fun setupGendersSpinner(genders: MutableList<Gender>) {
        val keyValueGenders = KeyValues(genders.map {
            KeyValue(
                it.name,
                it.id,
            )
        })
        (binding.genderDropDown as AutoCompleteTextView)
            .setAdapter(
                ArrayAdapter(
                    requireContext(),
                    R.layout.dropdown_menu_item,
                    keyValueGenders.keyValues
                )
            )
    }

    private fun initActionsListeners(viewModel: SignupViewModel) {
        binding.signupButton.setOnClickListener {
            getSignupRequest()?.let {
                viewModel.onSignupClick(it)
            }
        }
    }

    private fun getSignupRequest(): SignupRequest? {
        if (
            !validateTextLayouts(
                binding.editTextEmail,
                binding.editTextFirstName,
                binding.editTextLastName
            )
            || !binding.editTextEmail.isValidEmail()
            || !validateDropdownViews(binding.genderDropDown)
        ) {
            showError(getString(R.string.fill_fields_error_message))
            return null
        }

        return SignupRequest(
            binding.editTextEmail.stringContent,
            binding.editTextFirstName.stringContent,
            binding.editTextLastName.stringContent,
            binding.genderDropDown.getSelectedItemId()
        )
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

    companion object {
        fun newInstance() = SignupFragment()
    }

}