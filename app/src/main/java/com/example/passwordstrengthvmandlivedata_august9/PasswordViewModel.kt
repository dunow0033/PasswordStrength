package com.example.passwordstrengthvmandlivedata_august9

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PasswordViewModel : ViewModel() {

    private var _lowercasePresent: MutableLiveData<Boolean> = MutableLiveData()
    val lowercasePresent: LiveData<Boolean> = _lowercasePresent

    private var _uppercasePresent: MutableLiveData<Boolean> = MutableLiveData()
    val uppercasePresent: LiveData<Boolean> = _uppercasePresent

    private var _digitsPresent: MutableLiveData<Boolean> = MutableLiveData()
    val digitsPresent: LiveData<Boolean> = _digitsPresent

    private var _meetLengthRequirement: MutableLiveData<Boolean> = MutableLiveData()
    val meetLengthRequirement: LiveData<Boolean> = _meetLengthRequirement

    private var _enableButton: MutableLiveData<Boolean> = MutableLiveData(false)
    val enableButton: LiveData<Boolean> = _enableButton

    fun verifyPasswordStrength(input: String) {
        val hasLowercase = input.contains(Regex("[a-z]"))
        val hasUppercase = input.contains(Regex("[A-Z]"))
        val hasDigit = input.contains(Regex("[0-9]"))
        val meetsLength = input.length > 7

        _lowercasePresent.value = hasLowercase
        _uppercasePresent.value = hasUppercase
        _digitsPresent.value = hasDigit
        _meetLengthRequirement.value = meetsLength
        _enableButton.value = hasLowercase && hasUppercase && hasDigit && meetsLength
    }

}