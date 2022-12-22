package com.example.bankapp.Ui.Transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.Ui.coustomrs.CoustomrsAdapter
import com.example.bankapp.ViewModel.BankViewModel
import com.example.bankapp.databinding.TransactionItemBinding
import com.example.bankapp.databinding.TransactionsBinding


class Transactions : Fragment() {
    private lateinit var adapter: TransactionsAdapter
    private lateinit var bankViewModel: BankViewModel
    private lateinit var transactionsBinding: TransactionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bankViewModel = ViewModelProvider(this).get(BankViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        transactionsBinding = TransactionsBinding.inflate(inflater, container, false)

        setUpRV(transactionsBinding.transactionsRv)

        return transactionsBinding.root
    }

    fun setUpRV(recyclerView: RecyclerView) {
        adapter = TransactionsAdapter(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        bankViewModel.getTransactions().observe(viewLifecycleOwner, Observer { transactions ->
            adapter.setTransactions(transactions)
        })
    }
}