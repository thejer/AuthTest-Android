package com.appstyx.authtest.ui.signup

import androidx.lifecycle.*
import com.appstyx.authtest.data.DataRepository
import com.appstyx.authtest.data.model.Genders
import com.appstyx.authtest.data.model.SignupRequest
import com.appstyx.authtest.utils.DataResult
import com.appstyx.authtest.utils.LoadingStatus
import com.appstyx.authtest.utils.PrefsUtils
import kotlinx.coroutines.launch

class SignupViewModel(
    private val dataRepository: DataRepository,
    private val prefsUtils: PrefsUtils
) : ViewModel() {

    private val _genders = MutableLiveData<Genders>()
    val genders: LiveData<Genders>
        get() = _genders

    private val _token = MutableLiveData<String>()
    val token: LiveData<String>
        get() = _token

    private val _loadingStatus = MutableLiveData<LoadingStatus>()

    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus

    fun onSignupClick(signupRequest: SignupRequest) {
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.Loading
            when (val result = dataRepository.signup(signupRequest)) {
                is DataResult.Success -> {
                    _loadingStatus.value = LoadingStatus.Success
                    prefsUtils.accessToken = result.data.token
                    _token.value = result.data.token
                    prefsUtils.isLoggedIn = true
                }

                is DataResult.Error -> {
                    _loadingStatus.value =
                        LoadingStatus.Error(result.errorCode, result.errorMessage)
                }
            }
        }
    }

    fun getGenders() {
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.Loading
            when (val result = dataRepository.getGenders()) {
                is DataResult.Success -> {
                    _loadingStatus.value = LoadingStatus.Success
                    _genders.value = result.data
                }

                is DataResult.Error -> {
                    _loadingStatus.value =
                        LoadingStatus.Error(result.errorCode, result.errorMessage)
                }
            }
        }
    }

    class Factory(private val dataRepository: DataRepository, private val prefsUtils: PrefsUtils) :
        ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SignupViewModel(dataRepository, prefsUtils) as T
        }
    }
}