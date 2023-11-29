package uz.blog.uzpay.screen.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.blog.uzpay.screen.model.request.LoginRequest
import uz.blog.uzpay.screen.model.request.RegisterRequest
import uz.blog.uzpay.screen.model.response.AuthResponse
import uz.blog.uzpay.screen.repository.AuthRepository
import uz.blog.uzpay.screen.repository.selead.DataResult
import uz.blog.uzpay.screen.utils.PrefUtils
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository): ViewModel() {

    private var _errorLiveData = MutableLiveData<String>()
    var errorLiveData: LiveData<String> = _errorLiveData

    private var _progressLiveData = MutableLiveData<Boolean>()
    var progressLiveData: LiveData<Boolean> = _progressLiveData

    private var _registerLiveData = MutableLiveData<AuthResponse>()
    var registerLiveData: LiveData<AuthResponse> = _registerLiveData

    private var _loginLiveData = MutableLiveData<AuthResponse>()
    var loginLiveData: LiveData<AuthResponse> = _loginLiveData


    fun sendRegister(registerRequest: RegisterRequest){
        _progressLiveData.value = true
        viewModelScope.launch {
            when(val data = repository.registration(registerRequest)){
                is DataResult.Error -> {
                    _errorLiveData.value = data.message
                }
                is DataResult.Success -> {
                    _registerLiveData.value = data.result!!
                    PrefUtils.setToken(data.result.token)
                }
            }
            _progressLiveData.value = false
        }
    }

    fun getLogin(loginRequest: LoginRequest){
        _progressLiveData.value = true
        viewModelScope.launch {
            when(val data = repository.login(loginRequest)){
                is DataResult.Error -> {
                    _errorLiveData.value = data.message
                }
                is DataResult.Success -> {
                    _loginLiveData.value = data.result!!
                    PrefUtils.setToken(data.result.token)
                }
            }
            _progressLiveData.value = false
        }
    }
}