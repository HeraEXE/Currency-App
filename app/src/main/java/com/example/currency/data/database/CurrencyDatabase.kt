package com.example.currency.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.currency.data.models.Rate

@Database(entities = [Rate::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun getCurrencyDao(): CurrencyDao
}