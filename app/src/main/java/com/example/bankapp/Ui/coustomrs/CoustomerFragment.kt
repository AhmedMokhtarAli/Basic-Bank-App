package com.example.bankapp.Ui.coustomrs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bankapp.ViewModel.BankViewModel
import com.example.bankapp.databinding.CoustomerBinding


class CoustomerFragment : Fragment() {
    private val args by navArgs<CoustomerFragmentArgs>()
    private lateinit var binding:CoustomerBinding

    private lateinit var bankViewModel: BankViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= CoustomerBinding.inflate(inflater,container,false)


        bankViewModel=ViewModelProvider(this).get(BankViewModel::class.java)

        initViews()

        return binding.root
    }
    fun initViews(){
        binding.cNameTv.setText(args.coustomer.name)
        binding.cEmail.setText(args.coustomer.email)
        binding.cBalance.setText(BalanceFormat.formatBalance(args.coustomer.balance))
        binding.cDeposit.setOnClickListener(View.OnClickListener { deposit() })
    }

    fun deposit() {
        val amount: Float? = binding.cDepositAmountEt.editText?.text.toString().toFloat()
        if (amount != null) {
            navToDepositDialog(amount)
        } else{
            Toast.makeText(requireContext(),"enter amount",Toast.LENGTH_SHORT).show()
        }
    }
    fun navToDepositDialog(amount:Float){
        val action=CoustomerFragmentDirections.actionCoustomrToDepositDialog(args.coustomer,amount)
        binding.root.findNavController().navigate(action)
    }
}