package com.bhanupro.room_rxjava.persistance

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.reactivex.annotations.NonNull
import java.util.*

@Entity(tableName = "users")
data class User (
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "userid")
    var mId:String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "username")
    var userName:String

)