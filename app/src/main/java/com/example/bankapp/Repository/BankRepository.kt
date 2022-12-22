package com.example.bankapp.Repository

import androidx.lifecycle.LiveData
import com.example.bankapp.Data.BankDao
import com.example.bankapp.Model.Coustomer
import com.example.bankapp.Model.Transaction

class BankRepository(private val bankDao:BankDao) {
    fun getCoustomers():LiveData<List<Coustomer>>
    {
        return bankDao.getCoustomers()
    }

    suspend fun addCoustomers(coustomers:List<Coustomer>){
        bankDao.addCoustomers(coustomers)
    }

    suspend fun addTransaction(transaction:Transaction){
        bankDao.addTransfer(transaction)
    }
    suspend fun updateCoustomer(coustomer:Coustomer){
        bankDao.updateCoustomer(coustomer)
    }

    fun getTransactions():LiveData<List<Transaction>> {
       return bankDao.getTransactions()
    }
}