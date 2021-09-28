package com.example.currency.data.repository

import com.example.currency.data.models.CurrencyResponse
import com.example.currency.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    fun getLatestRates(): Flow<Resource<CurrencyResponse>>
}