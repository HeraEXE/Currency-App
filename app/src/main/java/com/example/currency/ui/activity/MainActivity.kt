package com.example.currency.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.currency.R
import com.example.currency.databinding.ActivityMainBinding
import com.example.currency.ui.fragments.FavoriteRatesFragmentDirections
import com.example.currency.ui.fragments.PopularRatesFragmentDirections
import com.example.currency.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

        binding.apply {
            btnPopular.setOnClickListener {
                val action = FavoriteRatesFragmentDirections.actionFavoriteRatesFragmentToPopularRatesFragment()
                navController.navigate(action)
                btnPopular.isClickable = false
                btnFavorite.isClickable = true
            }
            btnFavorite.setOnClickListener {
                val action = PopularRatesFragmentDirections.actionPopularRatesFragmentToFavoriteRatesFragment()
                navController.navigate(action)
                btnFavorite.isClickable = false
                btnPopular.isClickable = true
            }
            btnPopular.isClickable = false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}