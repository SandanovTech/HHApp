package com.example.hhapp.main.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.hhapp.R
import com.example.hhapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        binding.bottomMenu.setOnItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.search -> navHostFragment.findNavController().navigate(R.id.mainFragment)
                R.id.favorite -> navHostFragment.findNavController().navigate(R.id.favoriteFragment)
                else -> {}
            }
            true
        }
        navHostFragment.findNavController().addOnDestinationChangedListener {_, destination,_ ->
            if (destination.id == R.id.vacancyDetailsFragment) {
                binding.bottomMenu.visibility = View.GONE
            } else {
                binding.bottomMenu.visibility = View.VISIBLE
            }
        }
    }
}