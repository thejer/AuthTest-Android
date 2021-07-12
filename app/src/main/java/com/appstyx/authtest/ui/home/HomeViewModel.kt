package com.appstyx.authtest.ui.home

import androidx.lifecycle.*
import com.appstyx.authtest.data.DataRepository
import com.appstyx.authtest.data.model.UserData
import com.appstyx.authtest.utils.DataResult
import com.appstyx.authtest.utils.LoadingStatus
import com.appstyx.authtest.utils.PrefsUtils
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dataRepository: DataRepository,
    private val prefsUtils: PrefsUtils
) : ViewModel() {

    private val _user = MutableLiveData<UserData>()
    val user: LiveData<UserData>
        get() = _user

    private val _loadingStatus = MutableLiveData<LoadingStatus>()

    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus

    fun onLogoutClick() {
        prefsUtils.isLoggedIn = false
    }

    fun getUserData() {
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.Loading
            when (val result = dataRepository.getUser(prefsUtils.accessToken ?: "")) {
                is DataResult.Success -> {
                    _loadingStatus.value = LoadingStatus.Success
                    _user.value = result.data
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
            return HomeViewModel(dataRepository, prefsUtils) as T
        }
    }
}