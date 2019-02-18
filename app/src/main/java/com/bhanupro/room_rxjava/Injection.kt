package com.bhanupro.room_rxjava

import android.content.Context
import com.bhanupro.room_rxjava.persistance.LocalUserDatabase
import com.bhanupro.room_rxjava.persistance.UsersDatabase
import com.bhanupro.room_rxjava.ui.ViewModelFactory


object Injection {

    fun provideUserDataSource(context: Context): UserDataSource {
        val database = UsersDatabase.getInstance(context)
        return LocalUserDatabase(database?.userDao()!!)
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideUserDataSource(context)
        return ViewModelFactory(dataSource)
    }
}