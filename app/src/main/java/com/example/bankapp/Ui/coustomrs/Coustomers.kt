package com.example.bankapp.Ui.coustomrs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.Model.Coustomer
import com.example.bankapp.R
import com.example.bankapp.ViewModel.BankViewModel
import com.example.bankapp.databinding.CoustomersBinding


class Coustomers : Fragment(),CoustomrsAdapter.ClickListener {
    private lateinit var bankViewModel:BankViewModel
    private lateinit var binding:CoustomersBinding
    private lateinit var adapter: CoustomrsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //init view model
        bankViewModel=ViewModelProvider(this).get(BankViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= CoustomersBinding.inflate(inflater,container,false)

        //check database
        checkDataBase()

        setUpRV(binding.coustomersRv)

        return binding.root
    }
    fun setUpRV(recyclerView: RecyclerView){
        adapter= CoustomrsAdapter(requireContext(),false)
        adapter.setClickListner(this)
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(requireContext())

        bankViewModel.getCoustomers().observe(viewLifecycleOwner, Observer { coustomers ->
            adapter.setCoustomers(coustomers)
            })
    }
    fun checkDataBase(){
        bankViewModel.getCoustomers().observe(viewLifecycleOwner, Observer { coustomers ->
            if(coustomers.isEmpty()){
                bankViewModel.addCoustomers(creatCoustomeres())
            }
        })
    }


    fun creatCoustomeres():List<Coustomer>
    {
        var coustomers:MutableList<Coustomer>
        coustomers= mutableListOf<Coustomer>()
        coustomers.add(Coustomer(0,"Ahmed Mokhter","ahmedmokhter@gmail.com",500.00))
        coustomers.add(Coustomer(0,"Karam Jaber","karamjaber@gmail.com",250000.00))
        coustomers.add(Coustomer(0,"Ahmed Gamal","ahmedgamal@gmail.com",454000.00))
        coustomers.add(Coustomer(0,"Mohssan Mandoor","mohssanmandoor@gmail.com",100000.00))
        coustomers.add(Coustomer(0,"saleh Hassan","salehhassan@gmail.com",800000.00))
        coustomers.add(Coustomer(0,"Gamal Ibraheem","gamalibraheen@gmail.com",50000.00))
        coustomers.add(Coustomer(0,"khaled Mahmoud","khaledMahmoud@gmail.com",100000.00))
        coustomers.add(Coustomer(0,"hassan ahmed","hassanahmed@gmail.com",25000.00))
        coustomers.add(Coustomer(0,"Mostafa Mohammed","mostafamohamed@gmail.com",35000.00))
        coustomers.add(Coustomer(0,"kareem mohammed","kareemmohammed@gmail.com",600000.00))

        return coustomers}

    override fun onItemClick(coustomer: Coustomer) {
        navigateIntoCoustomer(coustomer)
    }
    fun navigateIntoCoustomer(coustomer: Coustomer) {
        val action=CoustomersDirections.actionCoustomrsToCoustomr(coustomer)
        binding.root.findNavController().navigate(action)
    }
}