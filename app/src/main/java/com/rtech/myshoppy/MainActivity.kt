package com.rtech.myshoppy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.rtech.myshoppy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var myNavController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        myNavController = navHostFragment.navController

        binding.shoppyBottomNavigationView.setupWithNavController(
            myNavController
        )

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.shoppyDashboardFragment, R.id.shoppyCartFragment, R.id.shoppyAccountFragment)
        )

        setupActionBarWithNavController(myNavController, appBarConfiguration)

        myNavController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.shoppyDashboardFragment -> {
                    Log.d("VTAG", "onCreate: shoppyDashboardFragment")
                }
                R.id.shoppyCartFragment -> {
                    Log.d("VTAG", "onCreate: shoppyCartFragment")
                }
                R.id.shoppyAccountFragment -> {
                    Log.d("VTAG", "onCreate: shoppyAccountFragment")
                }
            }
        }
    }
}