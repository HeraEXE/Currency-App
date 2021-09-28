package com.example.currency.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import java.util.*

@Dao
interface CurrencyDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currency: Currency) {

    }
}