package com.bhanupro.room_rxjava.ui

import androidx.lifecycle.ViewModel
import com.bhanupro.room_rxjava.UserDataSource
import com.bhanupro.room_rxjava.persistance.User
import io.reactivex.Completable
import io.reactivex.Flowable

class UserViewModel(dataSource: UserDataSource): ViewModel() {

    private var userDataSource:UserDataSource = dataSource
    private var user:User?=null

    fun getUserName():Flowable<String>{
        return userDataSource.getUser()
            .map {
                user = it
                return@map user!!.userName
            }
    }
    fun updateUserName(name:String):Completable{

        return if (user == null) {
            userDataSource.insertOrUpdateUser(User(userName = name))
        }else{
            userDataSource.insertOrUpdateUser(User(userName = name,mId = user!!.mId))
        }
    }

}