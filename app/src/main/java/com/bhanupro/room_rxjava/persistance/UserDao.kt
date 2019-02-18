package com.bhanupro.room_rxjava.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Query("SELECT * FROM USERS LIMIT 1")
    fun getUser():Flowable<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User):Completable

    @Query("DELETE FROM USERS")
    fun deleteUser()

}