package com.example.currency.data.repository

import com.example.currency.data.database.CurrencyDao
import com.example.currency.data.network.CurrencyApi
import com.example.currency.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: CurrencyApi,
    private val db: CurrencyDao
) : NetworkRepository, DatabaseRepository {

    override fun getLatestRates() = flow {
        emit(Resource.Loading())
        val response = try {
            api.getLatestRates()
        } catch (e: IOException) {
            emit(Resource.Failure(e.message))
            return@flow
        } catch (e: HttpException) {
            emit(Resource.Failure(e.message))
            return@flow
        }
        if (response.isSuccessful && response.body() != null) {
            emit(Resource.Success(response.body()!!))
        }
    }
}