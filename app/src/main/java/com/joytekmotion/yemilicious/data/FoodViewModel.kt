package com.joytekmotion.yemilicious.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodViewModel : ViewModel() {

    private val _text by lazy { MutableLiveData<String>() }
    val text: LiveData<String> = _text
}