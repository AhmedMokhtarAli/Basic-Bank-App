package com.example.bankapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bankapp.Data.BankDao
import com.example.bankapp.Data.BankDataBase
import com.example.bankapp.Model.Coustomer
import com.example.bankapp.Model.Transaction
import com.example.bankapp.Repository.BankRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BankViewModel(application: Application) : AndroidViewModel(application) {
   private val bankDao: BankDao
    private val bankRepository:BankRepository
    init {
        bankDao=BankDataBase.getInstance(application).bankDao()
        bankRepository= BankRepository(bankDao)
    }

    fun getCoustomers():LiveData<List<Coustomer>> {
        return bankRepository.getCoustomers()
    }

    fun addCoustomers(coustomers:List<Coustomer>) {
        viewModelScope.launch(Dispatchers.IO) {
            bankRepository.addCoustomers(coustomers)
        }
    }
    fun updateCoustomer(coustomer:Coustomer){
        viewModelScope.launch(Dispatchers.IO){
            bankRepository.updateCoustomer(coustomer)
        }
    }
    fun addTransaction(transaction: Transaction){
        viewModelScope.launch(Dispatchers.IO) {
            bankRepository.addTransaction(transaction)
        }
    }

    fun getTransactions():LiveData<List<Transaction>> {
        return bankRepository.getTransactions()
    }
}