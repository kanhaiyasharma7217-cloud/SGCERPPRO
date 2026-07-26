package com.shreeganpati.sgcerppro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shreeganpati.sgcerppro.model.LoginRequest
import com.shreeganpati.sgcerppro.model.LoginResponse
import com.shreeganpati.sgcerppro.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message

    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse: StateFlow<LoginResponse?> = _loginResponse

    fun requestOtp(mobile: String) {

        viewModelScope.launch {

            try {

                _loading.value = true

                val response = repository.requestOtp(mobile)

                if (response.isSuccessful) {

                    _message.value = "OTP Sent Successfully"

                } else {

                    _message.value = "Failed to Send OTP"

                }

            } catch (e: Exception) {

                _message.value = e.message ?: "Unknown Error"

            }

            _loading.value = false

        }

    }

    fun verifyOtp(
        mobile: String,
        otp: String,
        name: String = "",
        role: String = ""
    ) {

        viewModelScope.launch {

            try {

                _loading.value = true

                val response = repository.verifyOtp(
                    LoginRequest(
                        mobile_no = mobile,
                        otp_code = otp,
                        name = name,
                        role = role
                    )
                )

                if (response.isSuccessful) {

                    _loginResponse.value = response.body()

                    _message.value = "Login Successful"

                } else {

                    _message.value = "Invalid OTP"

                }

            } catch (e: Exception) {

                _message.value = e.message ?: "Login Failed"

            }

            _loading.value = false

        }

    }

}