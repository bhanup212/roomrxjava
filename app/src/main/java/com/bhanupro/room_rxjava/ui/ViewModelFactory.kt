package com.bhanupro.room_rxjava.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bhanupro.room_rxjava.UserDataSource

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(dataSource: UserDataSource):ViewModelProvider.Factory {

    private var userDataSource = dataSource


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(userDataSource) as T
        }
        throw IllegalArgumentException("Unknow Viewmodel class")
    }
}