package com.sdrss.zomatocloneapp.utils

import androidx.lifecycle.MutableLiveData

/**
 * @author SDRSS
 */
class NotNullMutableLiveData<T : Any>(defaultValue: T) : MutableLiveData<T>() {

    init {
        value = defaultValue
    }

    override fun getValue()  = super.getValue()!!
}