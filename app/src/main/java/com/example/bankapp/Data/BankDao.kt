package com.example.bankapp.Data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bankapp.Model.Coustomer
import com.example.bankapp.Model.Transaction

@Dao
interface BankDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCoustomers(coustomers:List<Coustomer>)

    @Query("select * from Coustomer ")
    fun getCoustomers():LiveData<List<Coustomer>>

    @Update
    suspend fun updateCoustomer(coustomer: Coustomer)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTransfer(transaction: Transaction)

    @Query("select * from 'transaction'")
    fun getTransactions():LiveData<List<Transaction>>
}