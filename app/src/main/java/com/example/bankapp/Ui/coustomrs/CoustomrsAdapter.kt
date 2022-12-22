package com.example.bankapp.Ui.coustomrs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.Model.Coustomer
import com.example.bankapp.databinding.CoustomerItemBinding

class CoustomrsAdapter(val context:Context,val isDeposit: Boolean) : RecyclerView.Adapter<CoustomrsAdapter.CoustomrViewHolder>() {

    private var coustomers = emptyList<Coustomer>()

    interface ClickListener {
        fun onItemClick(coustomer: Coustomer)
    }
    private lateinit var clickListener: ClickListener

    fun setClickListner(clickListener: ClickListener){
        this.clickListener=clickListener
    }

    fun setCoustomers(coustomers:List<Coustomer>) {
        this.coustomers=coustomers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoustomrViewHolder {
        val itemView=CoustomerItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return CoustomrViewHolder(itemView,clickListener)
    }

    override fun onBindViewHolder(holder: CoustomrViewHolder, position: Int) {
        val coustomer=coustomers.get(position)
        holder.bindCoustomers(coustomer,isDeposit)
    }

    override fun getItemCount(): Int =coustomers.size
    class CoustomrViewHolder(val coustomerBinding: CoustomerItemBinding,val clickListener: ClickListener) : RecyclerView.ViewHolder(coustomerBinding.root) {
        fun bindCoustomers(coustomer:Coustomer,isDeposit:Boolean){
            coustomerBinding.cousomerName.setText(coustomer.name)
            coustomerBinding.coustomrEmail.setText(coustomer.email)
            coustomerBinding.coustomerBalance.setText(BalanceFormat.formatBalance(coustomer.balance))
            coustomerBinding.coustomerItem.setOnClickListener(View.OnClickListener { clickListener.onItemClick(coustomer) })
        }

    }
}