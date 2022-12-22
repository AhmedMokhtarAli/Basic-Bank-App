package com.example.bankapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "transaction")
public data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val transferFrom:String,
    val transferTo: String,
    val amount:Double
)
