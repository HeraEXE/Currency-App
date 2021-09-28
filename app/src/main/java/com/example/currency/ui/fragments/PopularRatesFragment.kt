package com.example.currency.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.currency.R
import com.example.currency.adapters.RatesAdapter
import com.example.currency.data.models.Rate
import com.example.currency.data.models.CurrencyResponse
import com.example.currency.databinding.FragmentPopularRatesBinding
import com.example.currency.ui.activity.MainActivity
import com.example.currency.utils.Resource
import com.example.currency.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularRatesFragment : Fragment(R.layout.fragment_popular_rates) {

    private lateinit var viewModel: MainViewModel

    private val adapter = RatesAdapter()

    private var _binding: FragmentPopularRatesBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        _binding = FragmentPopularRatesBinding.bind(view)

        binding.rvPopular.adapter = adapter

        lifecycleScope.launch {
            viewModel.latestCurrency.collect {
                when (it) {
                    is Resource.Failure -> onFailure()
                    is Resource.Loading -> onLoading()
                    is Resource.Success -> onSuccess(it.data!!)
                }
            }
        }
    }


    private fun onFailure() {

    }

    private fun onLoading() {

    }

    private fun onSuccess(response: CurrencyResponse) {
        val ratesItems: MutableList<Rate> = mutableListOf()
        response.apply {
            ratesItems.add(Rate("$base/AED", rates.AED ?: 0.0))
            ratesItems.add(Rate("$base/AFN", rates.AFN?: 0.0))
            ratesItems.add(Rate("$base/ALL", rates.ALL?: 0.0))
            ratesItems.add(Rate("$base/AMD", rates.AMD?: 0.0))
            ratesItems.add(Rate("$base/ANG", rates.ANG?: 0.0))
            ratesItems.add(Rate("$base/AOA", rates.AOA?: 0.0))
            ratesItems.add(Rate("$base/ARS", rates.ARS?: 0.0))
            ratesItems.add(Rate("$base/AUD", rates.AUD?: 0.0))
            ratesItems.add(Rate("$base/AWG", rates.AWG?: 0.0))
            ratesItems.add(Rate("$base/AZN", rates.AZN?: 0.0))
            ratesItems.add(Rate("$base/BAM", rates.BAM?: 0.0))
        }
        adapter.submitList(ratesItems)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}