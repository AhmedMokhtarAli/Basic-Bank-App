package com.example.bankapp.Data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bankapp.Model.Transaction
import com.example.bankapp.Model.Coustomer

@Database(entities = [Coustomer::class,Transaction::class],
    version = 1,
    exportSchema = false)
abstract class BankDataBase : RoomDatabase() {
    public abstract fun bankDao():BankDao

    companion object
    {
        @Volatile
        private var INSTANCE : BankDataBase?=null

        fun getInstance(context : Context):BankDataBase
        {
            if (INSTANCE != null)
            {
                return INSTANCE as BankDataBase
            }
            synchronized(this)
            {
                INSTANCE=Room.databaseBuilder(context.applicationContext,
                BankDataBase::class.java,
                "BankDataBase").build()
            }
            return INSTANCE as BankDataBase
        }
    }
}