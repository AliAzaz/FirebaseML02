package com.abcd.firebasemlkit02.camera_image.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VModel : ViewModel() {
    var selected = MutableLiveData<String>()

    fun passData(data: String) {
        selected.value = data
    }

}