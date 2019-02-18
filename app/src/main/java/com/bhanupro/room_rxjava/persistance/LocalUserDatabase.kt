package com.bhanupro.room_rxjava.persistance

import com.bhanupro.room_rxjava.UserDataSource
import io.reactivex.Completable
import io.reactivex.Flowable

class LocalUserDatabase(dao: UserDao):UserDataSource {

    private var userDao:UserDao = dao

    override fun getUser(): Flowable<User> {
        return userDao.getUser()
    }

    override fun insertOrUpdateUser(user: User): Completable {
        return userDao.insertUser(user)
    }

    override fun deleteAllUsers() {
        return userDao.deleteUser()
    }
}