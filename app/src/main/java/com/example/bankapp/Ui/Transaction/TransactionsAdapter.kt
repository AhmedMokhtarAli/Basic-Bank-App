package com.example.bankapp.Ui.Transaction

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.Model.Coustomer
import com.example.bankapp.Model.Transaction
import com.example.bankapp.Ui.coustomrs.BalanceFormat
import com.example.bankapp.databinding.TransactionItemBinding

class TransactionsAdapter(val context: Context) : RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>() {
    private var transactions = emptyList<Transaction>()

    fun setTransactions(transactions:List<Transaction>) {
        this.transactions=transactions
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view= TransactionItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction=transactions.get(position)
        holder.bindTransaction(transaction)
    }

    override fun getItemCount(): Int =transactions.size

    class TransactionViewHolder(val transactionItemBinding: TransactionItemBinding) : RecyclerView.ViewHolder(transactionItemBinding.root){
        fun bindTransaction(transaction: Transaction) {
            transactionItemBinding.depositFrom.setText("From : "+transaction.transferFrom)
            transactionItemBinding.depositTo.setText("To : "+transaction.transferTo)
            transactionItemBinding.transactionAmount.setText(BalanceFormat.formatBalance(transaction.amount))
        }
    }
}