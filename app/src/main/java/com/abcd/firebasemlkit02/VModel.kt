package com.abcd.firebasemlkit02

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VModel : ViewModel() {
    val selected = MutableLiveData<String>()

    fun passData(data: String) {
        selected.value = data
    }

}