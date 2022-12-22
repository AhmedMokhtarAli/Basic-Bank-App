package com.example.bankapp.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "coustomer")
data class Coustomer(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    var name:String,
    var email:String,
    var balance:Double
) : Parcelable
