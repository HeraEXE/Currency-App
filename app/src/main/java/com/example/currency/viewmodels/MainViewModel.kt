package com.example.currency.viewmodels

import androidx.lifecycle.ViewModel
import com.example.currency.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val latestCurrency = repository.getLatestRates()
}