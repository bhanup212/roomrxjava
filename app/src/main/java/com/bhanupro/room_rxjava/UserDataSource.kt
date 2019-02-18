package com.bhanupro.room_rxjava

import com.bhanupro.room_rxjava.persistance.User
import io.reactivex.Completable
import io.reactivex.Flowable

interface UserDataSource {
    fun getUser():Flowable<User>
    fun insertOrUpdateUser(user: User):Completable
    fun deleteAllUsers()
}