package com.example.bankapp.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bankapp.R
import com.example.bankapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var controller:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment=supportFragmentManager.findFragmentById(R.id.navFragment) as NavHostFragment

        controller=navHostFragment.navController

        setupActionBarWithNavController(controller)
        manageBottomNav()
        binding.bottomNavView.setupWithNavController(controller)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()||controller.navigateUp()
    }

    fun manageBottomNav(){
       controller.addOnDestinationChangedListener{_,destination, _ ->
           if(destination.id==R.id.coustomers||destination.id==R.id.transactions){
               binding.bottomNavView.visibility=View.VISIBLE
           }
           else{
               binding.bottomNavView.visibility=View.GONE
           }
       }
    }

}