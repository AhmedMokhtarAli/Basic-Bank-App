package com.example.bankapp.Ui.coustomrs

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.Model.Coustomer
import com.example.bankapp.Model.Transaction
import com.example.bankapp.ViewModel.BankViewModel
import com.example.bankapp.databinding.DepositDialogBinding
import kotlin.properties.Delegates

class DepositDialog() :DialogFragment(),CoustomrsAdapter.ClickListener {
    private lateinit var bankViewModel: BankViewModel
    private lateinit var binding: DepositDialogBinding
    private lateinit var adapter: CoustomrsAdapter
    private val args by navArgs<DepositDialogArgs>()

    private lateinit var depositFrom:Coustomer
    private var amount:Double=0.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DepositDialogBinding.inflate(inflater,container,false)

        depositFrom=args.depositFrom
        amount=args.amount.toDouble()

        bankViewModel=ViewModelProvider(this).get(BankViewModel::class.java)

        setUpRV(binding.coustomersRvDialog)

        return binding.root
    }
    fun setUpRV(recyclerView: RecyclerView){
        adapter= CoustomrsAdapter(requireContext(),true)

        adapter.setClickListner(this)

        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(requireContext())

        bankViewModel.getCoustomers().observe(viewLifecycleOwner, Observer { coustomers ->
            adapter.setCoustomers(coustomers)
        })
    }

    override fun onItemClick(coustomer: Coustomer) {
        if (depositFrom.id==coustomer.id){
            Toast.makeText(requireContext(),"you can`t deposit for your self",Toast.LENGTH_SHORT)
        }
        else{
            confirmDeposit(coustomer)
        }

    }
    fun deposit(depositForCoustomer:Coustomer){
        val transaction=Transaction(0,depositFrom.name,depositForCoustomer.name,amount.toDouble())
        bankViewModel.addTransaction(transaction)
        updateCoustomers(depositForCoustomer)
        navToHome()
        Toast.makeText(requireContext(),"succefully deposited",Toast.LENGTH_SHORT)
    }

    fun navToHome(){
        val action=DepositDialogDirections.actionDepositDialogToCoustomers()
        binding.root.findNavController().navigate(action)
    }
    fun checkCoustomerBalance(balance:Double):Boolean{
        if(balance>=amount){
            return true
        }
        else{
            return false
        }
    }
   fun updateCoustomers(coustomer: Coustomer){
       depositFrom.balance=depositFrom.balance-amount
       coustomer.balance+=amount
       bankViewModel.updateCoustomer(depositFrom)
       bankViewModel.updateCoustomer(coustomer)
   }
    private fun confirmDeposit(depositForCoustomer:Coustomer) {
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            if(checkCoustomerBalance(depositFrom.balance)){
                deposit(depositForCoustomer)
            }
            else{
                Toast.makeText(requireContext(),"your balance isn`t enough ",Toast.LENGTH_SHORT)
            }
        }
        builder.setNegativeButton("No"){_,_ ->}
        builder.setTitle("Confirm Deposit")
        builder.setMessage("Are you sure you want to deposit ${BalanceFormat.formatBalance(amount)} $ for ${depositForCoustomer.name} ?")
        builder.create().show()
    }
}